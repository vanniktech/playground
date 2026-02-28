package app.playground.frontend

import androidx.compose.runtime.Immutable
import app.cash.sqldelight.db.SqlDriver
import com.russhwolf.settings.Settings
import kotlin.coroutines.CoroutineContext

@Immutable class PlaygroundDependencies(
  driver: SqlDriver,
  settingsFactory: Settings.Factory,
  val ioDispatcher: CoroutineContext,
) {
  val queryWrapper = createQueryWrapper(driver)
  val settings = settingsFactory.create()

  init {
    queryWrapper.fooQueries.selectCustom().executeAsOneOrNull()?.startDate
  }
}
