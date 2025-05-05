package app.playground

import androidx.compose.runtime.Immutable
import app.cash.sqldelight.db.SqlDriver
import com.russhwolf.settings.Settings
import kotlin.coroutines.CoroutineContext

@Immutable class FrontendDependencies(
  driver: SqlDriver,
  settingsFactory: Settings.Factory,
  val ioDispatcher: CoroutineContext,
) {
  val queryWrapper = createQueryWrapper(driver)
  val settings = settingsFactory.create()

  init {
    queryWrapper.fooQueries.updatePositionDurationMs(
      escape = "test"
    )
  }
}
