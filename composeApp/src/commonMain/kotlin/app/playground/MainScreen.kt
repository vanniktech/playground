package app.playground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage

@Composable internal fun MainScreen(
  navHostController: NavHostController,
) = LazyColumn {
  items(List(5) { it }) {
    AsyncImage(
      model = it,
      placeholder = ColorPainter(Color(red = it * 25, green = 255, blue = 255, alpha = 255)),
      error = ColorPainter(Color(red = it * 25, green = 255, blue = 255, alpha = 255)),
      modifier = Modifier.fillMaxWidth(1f).aspectRatio(1f),
      contentDescription = null,
      contentScale = ContentScale.Fit,
    )

  }
}
