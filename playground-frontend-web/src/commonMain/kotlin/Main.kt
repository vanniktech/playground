import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import app.cash.sqldelight.db.SqlDriver
import app.playground.frontend.PlaygroundApp
import app.playground.frontend.PlaygroundDependencies
import com.russhwolf.settings.Settings
import com.russhwolf.settings.StorageSettings
import kotlinx.coroutines.Dispatchers
import kotlin.js.ExperimentalWasmJsInterop

// TODO(nik) still broken!
@OptIn(ExperimentalComposeUiApi::class, ExperimentalWasmJsInterop::class)
fun main() = ComposeViewport {
  PlaygroundApp(
    PlaygroundDependencies(
      driver = driver(),
      settingsFactory = object : Settings.Factory {
        override fun create(name: String?): Settings = StorageSettings()
      },
      ioDispatcher = Dispatchers.Main,
    ),
  )
}

expect fun driver(): SqlDriver
