package app.playground

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import me.saket.telephoto.zoomable.ZoomSpec
import me.saket.telephoto.zoomable.rememberZoomableState
import me.saket.telephoto.zoomable.zoomable
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.vectorResource
import playground.composeapp.generated.resources.Res
import playground.composeapp.generated.resources.image

@Composable internal fun MainScreen(
  navHostController: NavHostController,
) = Box {
  Column {
    Image(
      modifier = Modifier.fillMaxWidth().aspectRatio(1f)
        .zIndex(Float.MAX_VALUE)
        .zoomable(rememberZoomableState(  zoomSpec = ZoomSpec(
          maxZoomFactor = 1f,
          preventOverOrUnderZoom = false,
        ),), clipToBounds = false),
      bitmap = imageResource(Res.drawable.image),
      contentDescription = null,
    )

    TextField(
      value = "",
      onValueChange = { },
    )
  }

  Column {
    Spacer(Modifier.weight(1f))

    Button(
      modifier = Modifier.fillMaxWidth(),
      onClick = {

      }
    ) {
      Text("Test")
    }

    Spacer(Modifier.height(16.dp))
  }
}
