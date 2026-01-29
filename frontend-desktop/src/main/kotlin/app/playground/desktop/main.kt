package app.playground.desktop

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import app.playground.App
import app.playground.FrontendDependencies
import app.playground.migrateIfNeeded
import com.russhwolf.settings.PreferencesSettings
import kotlinx.coroutines.Dispatchers
import java.awt.Dimension
import java.util.prefs.Preferences

fun main() {
  val sqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
  migrateIfNeeded(
    driver = sqlDriver,
  )

  application {
    Window(
      onCloseRequest = ::exitApplication,
      state = rememberWindowState(width = 800.dp, height = 600.dp),
      alwaysOnTop = true,
      title = "Playground",
    ) {
      window.minimumSize = Dimension(350, 600)

      App(
        FrontendDependencies(
          driver = sqlDriver,
          settingsFactory = PreferencesSettings.Factory(Preferences.userRoot().node("app.playground")),
          ioDispatcher = Dispatchers.IO,
        ),
      )
    }
  }
}
