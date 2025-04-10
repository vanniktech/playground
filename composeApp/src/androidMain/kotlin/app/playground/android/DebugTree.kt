package app.playground.android

import timber.log.Timber

class DebugTree : Timber.DebugTree() {
  public override fun createStackElementTag(element: StackTraceElement) = super.createStackElementTag(element) + ":" + element.lineNumber
}
