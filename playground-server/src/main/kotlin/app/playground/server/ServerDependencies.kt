package app.playground.server

import app.playground.network.JSON
import app.playground.server.database.createQueryWrapper
import com.zaxxer.hikari.HikariDataSource

internal class ServerDependencies(
  private val dataSource: HikariDataSource,
) {
  private val queryWrapper = createQueryWrapper(dataSource = dataSource)

  val json = JSON
}
