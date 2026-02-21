package app.playground.frontend

import androidx.compose.ui.window.ComposeUIViewController
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import app.playground.frontend.database.QueryWrapper
import com.russhwolf.settings.NSUserDefaultsSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
  val viewController = ComposeUIViewController {
    PlaygroundApp(
      dependencies = PlaygroundDependencies(
        driver = NativeSqliteDriver(QueryWrapper.Schema.synchronous(), DATABASE_NAME),
        settingsFactory = NSUserDefaultsSettings.Factory(),
        ioDispatcher = Dispatchers.IO,
      ),
    )
  }

  return viewController
}
