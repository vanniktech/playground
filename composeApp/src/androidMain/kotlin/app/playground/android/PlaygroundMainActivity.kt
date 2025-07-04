package app.playground.android

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.media.MediaCodec
import android.media.MediaExtractor
import android.media.MediaFormat
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import timber.log.Timber
import java.io.File
import kotlin.math.cos
import kotlin.math.sin

class PlaygroundMainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    enableEdgeToEdge(
      statusBarStyle = SystemBarStyle.light(
        Color.TRANSPARENT,
        Color.TRANSPARENT,
      ),
      navigationBarStyle = SystemBarStyle.light(
        Color.TRANSPARENT,
        Color.TRANSPARENT,
      ),
    )
    super.onCreate(savedInstanceState)

    // works.
    "test".forEach {

    }

    val test = copy("audio.wav")
    // val test = copy("whatsapp.oga")
    // val test = copy("audio-very-short.wav")

    val view = WaveformView(this)
    view.foo(test.absolutePath)
    setContentView(view)
    // setContent {
    //   App(
    //     frontendDependencies = (application as PlaygroundApplication).dependencies,
    //   )
    // }
  }

  private fun copy(name: String): File {
    val resolve = filesDir.resolve(name)
    assets.open(name).copyTo(resolve.outputStream())
    return resolve
  }
}

class WaveformView(context: android.content.Context) : View(context) {
  private val paint = Paint().apply {
    color = Color.BLUE
    strokeWidth = 2f
  }
  private var amplitudes: FloatArray? = null

  fun foo(path: String) {
    // Load and process the MP3 file in a background thread
    Thread {
      amplitudes = generateWaveform(path)
      postInvalidate() // Trigger redraw
    }.start()
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    amplitudes?.let { amps ->
      Timber.tag("NIII").i("${amps.size} amplitudes")
      val width = width.toFloat()
      val height = height.toFloat()
      val centerY = height / 2
      val stepX = width / amps.size

      for (i in amps.indices) {
        val x = i * stepX
        val y = centerY - (amps[i] * centerY) // Scale amplitude to fit height
        canvas.drawLine(x, centerY, x, y, paint)
      }
    }

    amplitudes?.let { amps ->
      val centerX = width / 2f
      val centerY = height / 2f
      val baseRadius = minOf(centerX, centerY) * 0.2f // Base circle size
      val maxAmplitudeLength = baseRadius * 2f // Max length of amplitude lines

      // Angle step for each amplitude (full circle = 360°)
      val angleStep = 360f / amps.size
      var currentAngle = -90f // Start at 12 o'clock (-90° because 0° is 3 o'clock)

      for (amplitude in amps) {
        // Convert angle to radians
        val radians = Math.toRadians(currentAngle.toDouble())

        // Base point on the circle (without amplitude)
        val baseX = centerX + baseRadius * cos(radians).toFloat()
        val baseY = centerY + baseRadius * sin(radians).toFloat()

        // End point scaled by amplitude
        val amplitudeLength = amplitude * maxAmplitudeLength // Scale amplitude (0.0 to 1.0)
        val endX = centerX + (baseRadius + amplitudeLength) * cos(radians).toFloat()
        val endY = centerY + (baseRadius + amplitudeLength) * sin(radians).toFloat()

        // Draw line from base to amplitude endpoint
        canvas.drawLine(baseX, baseY, endX, endY, paint)

        currentAngle += angleStep
      }
    }
  }

  private fun generateWaveform(filePath: String): FloatArray? {
    val extractor = MediaExtractor()
    extractor.setDataSource(filePath)

    val format = extractor.getTrackFormat(0)
    extractor.selectTrack(0)

    val codec = MediaCodec.createDecoderByType(format.getString(MediaFormat.KEY_MIME)!!)
    codec.configure(format, null, null, 0)
    codec.start()

    val sampleRate = format.getInteger(MediaFormat.KEY_SAMPLE_RATE)
    val bufferInfo = MediaCodec.BufferInfo()
    val amplitudes = mutableListOf<Float>()
    var totalSamples = 0

    var max = 0.0f

    while (true) {
      val inputBufferId = codec.dequeueInputBuffer(10000)

      if (inputBufferId >= 0) {
        val inputBuffer = codec.getInputBuffer(inputBufferId)!!
        val sampleSize = extractor.readSampleData(inputBuffer, 0)
        if (sampleSize < 0) break

        codec.queueInputBuffer(inputBufferId, 0, sampleSize, extractor.sampleTime, 0)
        extractor.advance()
      }

      val outputBufferId = codec.dequeueOutputBuffer(bufferInfo, 10000)
      if (outputBufferId >= 0) {
        val outputBuffer = codec.getOutputBuffer(outputBufferId)!!
        val pcmData = ShortArray(bufferInfo.size / 2)
        outputBuffer.asShortBuffer().get(pcmData)

        // Downsample: Take average amplitude per chunk
        val chunkSize = pcmData.size / 1000 // Adjust for desired resolution
        for (i in pcmData.indices step chunkSize) {
          var sum = 0f
          val chunkEnd = minOf(i + chunkSize, pcmData.size)
          for (j in i until chunkEnd) {
            sum += kotlin.math.abs(pcmData[j].toFloat() / Short.MAX_VALUE)
          }
          val amplitude = sum / (chunkEnd - i)
          max = maxOf(max, amplitude)
          amplitudes.add(amplitude)
        }

        totalSamples += pcmData.size
        codec.releaseOutputBuffer(outputBufferId, false)
      }
    }

    Timber.tag("NII").i("max is $max")
    codec.stop()
    codec.release()
    extractor.release()

    return amplitudes.toFloatArray()
  }
}
