package app.playground

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import kotlinx.coroutines.Dispatchers

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
  ComposeViewport(document.body!!) {
    App(
      FrontendDependencies(
        ioDispatcher = Dispatchers.Main,
        // sqlDriver = ,
        // settingsFactory = ,
      ),
    )
  }
}
