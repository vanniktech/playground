package app.playground.server.database

import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import javax.sql.DataSource

internal fun createQueryWrapper(dataSource: DataSource): QueryWrapper {
  val driver = dataSource.asJdbcDriver()

  return QueryWrapper(
    driver = driver,
  )
}
