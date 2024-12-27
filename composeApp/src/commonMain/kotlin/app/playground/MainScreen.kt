package app.playground

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import me.saket.telephoto.zoomable.ZoomSpec
import me.saket.telephoto.zoomable.rememberZoomableState
import me.saket.telephoto.zoomable.zoomable
import org.jetbrains.compose.resources.painterResource
import playground.composeapp.generated.resources.Res
import playground.composeapp.generated.resources.kangaroo
import playground.composeapp.generated.resources.nashorn

@Composable internal fun MainScreen(
  navHostController: NavHostController,
) = LazyColumn {
  item("image-1") {
    Image(painterResource(Res.drawable.nashorn), contentDescription = null, modifier = Modifier.magicZoom())
  }

  item("image-2") {
    Image(painterResource(Res.drawable.kangaroo), contentDescription = null, modifier = Modifier.magicZoom())
  }

  item("image-3") {
    Image(painterResource(Res.drawable.nashorn), contentDescription = null, modifier = Modifier.magicZoom())
  }
}

@Composable fun Modifier.magicZoom(): Modifier {
  val state = rememberZoomableState(
    zoomSpec = ZoomSpec(
      maxZoomFactor = 1f,
      preventOverOrUnderZoom = false,
    ),
  )
  val isZoomedIn = (state.zoomFraction ?: 0f) > 0f
  return zIndex(if (isZoomedIn) Float.MAX_VALUE else 1f).zoomable(
    state,
    clipToBounds = false,
  )
}
