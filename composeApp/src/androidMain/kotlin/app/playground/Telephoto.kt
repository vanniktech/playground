package app.playground

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.saket.telephoto.zoomable.rememberZoomablePeekOverlayState
import me.saket.telephoto.zoomable.zoomablePeekOverlay

@Composable actual fun Modifier.magicZoom2() = zoomablePeekOverlay(
  state = rememberZoomablePeekOverlayState(

  )
)
