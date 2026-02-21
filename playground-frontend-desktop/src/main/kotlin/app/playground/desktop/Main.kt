package app.playground.desktop

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import app.playgorund.frontend.Res
import app.playgorund.frontend.playground_app_name
import app.playground.frontend.DATABASE_NAME
import app.playground.frontend.PlaygroundApp
import app.playground.frontend.PlaygroundDependencies
import app.playground.frontend.database.QueryWrapper
import app.playground.frontend.migrateIfNeeded
import com.russhwolf.settings.PreferencesSettings
import kotlinx.coroutines.Dispatchers
import okio.Path.Companion.toPath
import org.jetbrains.compose.resources.stringResource
import java.awt.Dimension
import java.util.prefs.Preferences

enum class DesktopOs {
  MAC,
  UNIX,
  WINDOWS,
}

fun main() {
  val osName = System.getProperty("os.name").lowercase()
  val desktopOs = when {
    "win" in osName -> DesktopOs.WINDOWS
    "mac" in osName -> DesktopOs.MAC
    "nix" in osName || "nux" in osName -> DesktopOs.UNIX
    else -> error("Can't map \"$osName\"")
  }

  val root = (
    when (desktopOs) {
      DesktopOs.MAC -> System.getProperty("user.home").toPath() / "Library/"
      DesktopOs.UNIX -> "/etc/".toPath()
      DesktopOs.WINDOWS -> System.getenv("APPDATA").toPath()
    } /
      "Application Support" /
      "Playground"
    ).normalized()

  root.toFile().mkdirs()

  val sqlDriver = JdbcSqliteDriver(
    url = "jdbc:sqlite:${root / DATABASE_NAME}",
    schema = QueryWrapper.Schema.synchronous(),
  )

  migrateIfNeeded(
    driver = sqlDriver,
  )

  application {
    Window(
      onCloseRequest = ::exitApplication,
      state = rememberWindowState(width = 800.dp, height = 600.dp),
      alwaysOnTop = false,
      title = stringResource(Res.string.playground_app_name),
    ) {
      window.minimumSize = Dimension(350, 600)

      PlaygroundApp(
        PlaygroundDependencies(
          driver = sqlDriver,
          settingsFactory = PreferencesSettings.Factory(Preferences.userRoot().node("app.playground")),
          ioDispatcher = Dispatchers.IO,
        ),
      )
    }
  }
}
