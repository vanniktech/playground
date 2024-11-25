package app.playground

import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.Density
import kotlinx.cinterop.set
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.skia.ColorAlphaType
import org.jetbrains.skia.ColorType
import org.jetbrains.skia.Image
import org.jetbrains.skia.ImageInfo
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.withSign

@Composable
actual fun rememberBlurHashPainter(blurHash: BlurHashModel): Painter {
  // todo: can i read the density from DrawScope instead?
  val density = LocalDensity.current
  val isInPreview = LocalInspectionMode.current
  return remember(blurHash, density) {
    AsyncBlurHashPainter(blurHash, density, isInPreview)
  }
}

private class AsyncBlurHashPainter(
  private val blurHash: BlurHashModel,
  private val density: Density,
  private val isInPreview: Boolean,
) : Painter(),
  RememberObserver {

  private var painter: Painter? by mutableStateOf(null)
  private var scope: CoroutineScope? = null
  private var alpha: Float = DefaultAlpha
  private var colorFilter: ColorFilter? = null

  override val intrinsicSize: Size
    get() = painter?.intrinsicSize ?: Size.Unspecified

  override fun onRemembered() {
    if (isInPreview) {
      painter = blurHash.createPainter(density)
    } else {
      scope = CoroutineScope(Dispatchers.Main.immediate).apply {
        launch {
          painter = withContext(Dispatchers.Main) {
            blurHash.createPainter(density)
          }
        }
      }
    }
  }

  override fun onForgotten() {
    scope?.cancel()
  }

  override fun onAbandoned() = Unit

  override fun applyAlpha(alpha: Float): Boolean {
    this.alpha = alpha
    return true
  }

  override fun applyColorFilter(colorFilter: ColorFilter?): Boolean {
    this.colorFilter = colorFilter
    return true
  }

  override fun DrawScope.onDraw() {
    painter?.let { painter ->
      with(painter) {
        draw(size, alpha, colorFilter)
      }
    }
  }

  private fun BlurHashModel.createPainter(density: Density): Painter {
    check(width > 0 && height > 0)

    // Smaller bitmaps are significantly cheaper to generate with little reduction in blur quality.
    val maxWidthPx = density.run { 80.dp.roundToPx() }
    val aspectRatio = width.toFloat() / height
    val constrainedWidth = (width / 4).coerceAtMost(maxWidthPx)

    val pixels = CommonBlurHash.decode(
      blurHash = blurHash.hash,
      pixelWriter = PixelWriterArgb8888(width = width, height = height),
      width = constrainedWidth,
      height = (constrainedWidth / aspectRatio).roundToInt(),
      punch = 1f,
      useCache = true,
    )

    return when (pixels) {
      null -> ColorPainter(Color.Transparent)
      else -> BitmapPainter(imageFromIntArray(pixels, constrainedWidth).toComposeImageBitmap())
    }
  }
}

// TODO: improve this to do it in one step & try to correctly infer the proper size!
fun makeByteArrayFromRGBArray(pixArray: IntArray): ByteArray {
  val result = ByteArray(pixArray.size * 4)
  var off = 0
  for (pix in pixArray) {
    result[off++] = (pix shr 16 and 0xff).toByte()
    result[off++] = (pix shr 8 and 0xff).toByte()
    result[off++] = (pix and 0xff).toByte()
    result[off++] = (pix shr 24 and 0xff).toByte()
  }

  return result
}

fun imageFromIntArray(pixArray: IntArray, imageWidth: Int) = Image.makeRaster(
  imageInfo = ImageInfo(imageWidth, pixArray.size / imageWidth, ColorType.RGBA_8888, ColorAlphaType.UNPREMUL),
  // Four bytes per pixel.
  rowBytes = imageWidth * 4,
  bytes = makeByteArrayFromRGBArray(pixArray),
)

internal interface PixelWriter<T : Any> {
  fun write(x: Int, y: Int, width: Int, red: Int, green: Int, blue: Int)

  fun get(): T
}

internal class PixelWriterArgb8888(
  width: Int,
  height: Int,
) : PixelWriter<IntArray> {
  private val pixels = IntArray(width * height)

  override fun write(
    x: Int,
    y: Int,
    width: Int,
    red: Int,
    green: Int,
    blue: Int,
  ) {
    pixels[x + width * y] = 0xFF000000.toInt() or (red shl 16) or (green shl 8) or blue
  }

  override fun get() = pixels
}
internal class BlurHashInfo internal constructor(
  val colors: Array<FloatArray>,
  val componentX: Int,
  val componentY: Int,
)

internal object CommonBlurHash {
  // Cache Math.cos() calculations to improve performance.
  // The number of calculations can be huge for many bitmaps: width * height * numCompX * numCompY * 2 * nBitmaps
  // The cache is enabled by default, it is recommended to disable it only when just a few images are displayed
  private val cacheCosinesX = HashMap<Int, FloatArray>()
  private val cacheCosinesY = HashMap<Int, FloatArray>()

  internal fun clearCache() {
    cacheCosinesX.clear()
    cacheCosinesY.clear()
  }

  internal fun averageColor(
    blurHash: String,
    punch: Float,
  ): Int? {
    val blurHashInfo = info(
      blurHash = blurHash,
      punch = punch,
    ) ?: return null

    val dc = blurHashInfo.colors.firstOrNull() ?: return null
    val red = Utils.linearToSrgb(dc[0])
    val green = Utils.linearToSrgb(dc[1])
    val blue = Utils.linearToSrgb(dc[2])
    return 255 shl 24 or (red shl 16) or (green shl 8) or blue
  }

  internal fun <T : Any> decode(
    blurHash: String,
    pixelWriter: PixelWriter<T>,
    width: Int,
    height: Int,
    punch: Float,
    useCache: Boolean,
  ): T? {
    val blurHashInfo = info(
      blurHash = blurHash,
      punch = punch,
    ) ?: return null

    return composePixels(
      pixelWriter = pixelWriter,
      width = width,
      height = height,
      componentX = blurHashInfo.componentX,
      componentY = blurHashInfo.componentY,
      colors = blurHashInfo.colors,
      useCache = useCache,
    )
  }

  private fun info(
    blurHash: String,
    punch: Float,
  ): BlurHashInfo? {
    if (blurHash.length < 6) {
      return null
    }

    val numCompEnc = Base83.decode83(blurHash, 0, 1)
    val componentX = (numCompEnc % 9) + 1
    val componentY = (numCompEnc / 9) + 1

    if (blurHash.length != 4 + 2 * componentX * componentY) {
      return null
    }

    val maxAcEnc = Base83.decode83(blurHash, 1, 2)
    val maxAc = (maxAcEnc + 1) / 166f
    val colors = Array(componentX * componentY) { i ->
      if (i == 0) {
        val colorEnc = Base83.decode83(blurHash, 2, 6)
        Utils.decodeDc(colorEnc)
      } else {
        val from = 4 + i * 2
        val colorEnc = Base83.decode83(blurHash, from, from + 2)
        Utils.decodeAc(colorEnc, maxAc * punch)
      }
    }

    return BlurHashInfo(
      colors = colors,
      componentX = componentX,
      componentY = componentY,
    )
  }

  private fun <T : Any> composePixels(
    pixelWriter: PixelWriter<T>,
    width: Int,
    height: Int,
    componentX: Int,
    componentY: Int,
    colors: Array<FloatArray>,
    useCache: Boolean,
  ): T {
    val calculateCosX = !useCache || !cacheCosinesX.containsKey(width * componentX)
    val cosinesX = getArrayForCosinesX(calculateCosX, width, componentX)
    val calculateCosY = !useCache || !cacheCosinesY.containsKey(height * componentY)
    val cosinesY = getArrayForCosinesY(calculateCosY, height, componentY)
    for (y in 0 until height) {
      for (x in 0 until width) {
        var r = 0f
        var g = 0f
        var b = 0f
        for (j in 0 until componentY) {
          for (i in 0 until componentX) {
            val cosX = cosinesX.getCos(calculateCosX, i, componentX, x, width)
            val cosY = cosinesY.getCos(calculateCosY, j, componentY, y, height)
            val basis = (cosX * cosY)
            val color = colors[j * componentX + i]
            r += color[0] * basis
            g += color[1] * basis
            b += color[2] * basis
          }
        }

        pixelWriter.write(
          x = x,
          y = y,
          width = width,
          red = Utils.linearToSrgb(r),
          green = Utils.linearToSrgb(g),
          blue = Utils.linearToSrgb(b),
        )
      }
    }

    return pixelWriter.get()
  }

  private fun getArrayForCosinesY(calculate: Boolean, height: Int, numCompY: Int) = when {
    calculate -> FloatArray(height * numCompY).also { cacheCosinesY[height * numCompY] = it }
    else -> cacheCosinesY[height * numCompY]!!
  }

  private fun getArrayForCosinesX(calculate: Boolean, width: Int, numCompX: Int) = when {
    calculate -> FloatArray(width * numCompX).also { cacheCosinesX[width * numCompX] = it }
    else -> cacheCosinesX[width * numCompX]!!
  }

  private fun FloatArray.getCos(
    calculate: Boolean,
    x: Int,
    numComp: Int,
    y: Int,
    size: Int,
  ): Float {
    val index = x + numComp * y
    if (calculate) {
      this[index] = cos(PI * y * x / size).toFloat()
    }

    return this[index]
  }
}

internal object Base83 {
  val CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz#$%*+,-.:;=?@[]^_{|}~".toCharArray()

  internal fun encode83(
    value: Int,
    length: Int,
    buffer: CharArray,
    offset: Int,
  ) {
    var exp = 1
    var i = 1
    while (i <= length) {
      val digit = (value / exp % CHARS.size)
      buffer[offset + length - i] = CHARS[digit]
      i++
      exp *= CHARS.size
    }
  }

  fun decode83(value: String, from: Int = 0, to: Int = value.length): Int {
    var result = 0
    val chars = value.toCharArray()
    for (i in from until to) {
      result = result * CHARS.size + CHARS.indexOf(chars[i])
    }
    return result
  }
}

internal object Utils {
  private fun srgbToLinear(value: Int): Float {
    val v = value / 255f
    return if (v <= 0.04045f) {
      v / 12.92f
    } else {
      ((v + 0.055f) / 1.055f).pow(2.4f)
    }
  }

  internal fun linearToSrgb(value: Float): Int {
    val v = value.coerceIn(0f, 1f)
    return if (v <= 0.0031308f) {
      (v * 12.92f * 255f + 0.5f).toInt()
    } else {
      ((1.055f * v.pow(1 / 2.4f) - 0.055f) * 255 + 0.5f).toInt()
    }
  }

  private fun signPow(value: Float, exp: Float) = abs(value).pow(exp).withSign(value)

  internal fun max(
    values: Array<FloatArray>,
    from: Int,
    endExclusive: Int,
  ): Float {
    var result = Float.NEGATIVE_INFINITY
    for (i in from until endExclusive) {
      for (j in values[i].indices) {
        val value = values[i][j]
        if (value > result) {
          result = value
        }
      }
    }
    return result
  }

  internal fun encodeAc(
    value: FloatArray,
    maximumValue: Float,
  ): Int {
    val quantR = floor((signPow(value[0] / maximumValue, 0.5f) * 9f + 9.5f).coerceIn(0f, 18f))
    val quantG = floor((signPow(value[1] / maximumValue, 0.5f) * 9f + 9.5f).coerceIn(0f, 18f))
    val quantB = floor((signPow(value[2] / maximumValue, 0.5f) * 9f + 9.5f).coerceIn(0f, 18f))
    return (quantR * 19 * 19 + quantG * 19 + quantB).roundToInt()
  }

  internal fun decodeAc(value: Int, maxAc: Float): FloatArray {
    val r = value / (19 * 19)
    val g = (value / 19) % 19
    val b = value % 19
    return floatArrayOf(
      signPow((r - 9) / 9f, 2f) * maxAc,
      signPow((g - 9) / 9f, 2f) * maxAc,
      signPow((b - 9) / 9f, 2f) * maxAc,
    )
  }

  internal fun encodeDc(value: FloatArray): Int {
    val r = linearToSrgb(value[0])
    val g = linearToSrgb(value[1])
    val b = linearToSrgb(value[2])
    return (r shl 16) + (g shl 8) + b
  }

  internal fun decodeDc(colorEnc: Int): FloatArray {
    val r = (colorEnc shr 16) and 255
    val g = (colorEnc shr 8) and 255
    val b = colorEnc and 255
    return floatArrayOf(srgbToLinear(r), srgbToLinear(g), srgbToLinear(b))
  }
}
