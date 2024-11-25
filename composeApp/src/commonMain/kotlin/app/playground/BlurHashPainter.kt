package app.playground

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

data class BlurHashModel(
  val hash: String,
  val width: Int,
  val height: Int,
)

@Composable
expect fun rememberBlurHashPainter(blurHash: BlurHashModel): Painter
