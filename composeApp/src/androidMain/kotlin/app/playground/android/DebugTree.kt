package app.playground.android

import androidx.compose.ui.Modifier
import me.saket.telephoto.zoomable.zoomablePeekOverlay
import timber.log.Timber

class DebugTree : Timber.DebugTree() {
  public override fun createStackElementTag(element: StackTraceElement) = super.createStackElementTag(element) + ":" + element.lineNumber
}
