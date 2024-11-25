package app.playground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Density
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

@Composable internal fun MainScreen(
  navHostController: NavHostController,
) = Column {
  Text("Hello World")

  val placeholder = rememberBlurHashPainter(
    BlurHashModel(
      hash = "LEHLk~WB2yk8pyo0adR*.7kCMdnj",
      width = 100,
      height = 100,
    ),
  )

  AsyncImage(
    model = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fcommons.wikimedia.org%2Fwiki%2FFile%3ATest.svg&psig=AOvVaw0YE5gD2JedL-Twca2x2_us&ust=1732595398709000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCKiigv7S9okDFQAAAAAdAAAAABAE",
    contentScale = ContentScale.Fit,
    contentDescription = null,
    placeholder = placeholder,
    error = placeholder,
    modifier = Modifier.fillMaxWidth(fraction = 1f).aspectRatio(1f),
  )
}
