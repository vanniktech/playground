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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import app.playground.BlurHashModel
import com.vanniktech.blurhash.BlurHash
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

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
          painter = withContext(Dispatchers.IO) {
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

    val bitmap = BlurHash.decode(
      blurHash = hash,
      width = constrainedWidth,
      height = (constrainedWidth / aspectRatio).roundToInt(),
    )

    return when (bitmap) {
      null -> ColorPainter(Color.Transparent)
      else -> BitmapPainter(bitmap.asImageBitmap())
    }
  }
}
