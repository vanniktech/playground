package app.playground.android

import android.app.Application
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import app.playground.frontend.DATABASE_NAME
import app.playground.frontend.PlaygroundDependencies
import app.playground.frontend.database.QueryWrapper
import com.russhwolf.settings.SharedPreferencesSettings
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

class PlaygroundApplication : Application() {
  val dependencies by lazy(LazyThreadSafetyMode.NONE) {
    PlaygroundDependencies(
      driver = AndroidSqliteDriver(QueryWrapper.Schema.synchronous(), this, DATABASE_NAME),
      settingsFactory = SharedPreferencesSettings.Factory(this),
      ioDispatcher = Dispatchers.IO,
    )
  }

  override fun onCreate() {
    super.onCreate()

    Timber.plant(DebugTree())
  }
}
