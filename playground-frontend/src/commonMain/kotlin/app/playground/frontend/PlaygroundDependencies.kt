package app.playground.frontend

import androidx.compose.runtime.Immutable
import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import com.russhwolf.settings.Settings
import kotlinx.serialization.Serializable
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.JvmInline

@Immutable class PlaygroundDependencies(
  driver: SqlDriver,
  settingsFactory: Settings.Factory,
  val ioDispatcher: CoroutineContext,
) {
  val queryWrapper = createQueryWrapper(driver)
  val settings = settingsFactory.create()

  init {
    queryWrapper.fooQueries.sum().executeAsOneOrNull()?.sum
  }
}

@JvmInline @Serializable value class Amount(val value: Int)
