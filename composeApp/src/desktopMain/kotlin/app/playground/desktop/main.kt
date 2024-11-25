package app.playground.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import app.playground.App
import app.playground.FrontendDependencies
import app.playground.migrateIfNeeded
import com.russhwolf.settings.PreferencesSettings
import kotlinx.coroutines.Dispatchers

fun main(args: Array<String>) {
  val sqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
  migrateIfNeeded(
    driver = sqlDriver,
  )

  application {
    Window(
      onCloseRequest = ::exitApplication,
      title = "Playground",
    ) {
      App(
        FrontendDependencies(
          driver = sqlDriver,
          settingsFactory = PreferencesSettings.Factory(),
          ioDispatcher = Dispatchers.IO,
        ),
      )
    }
  }
}
