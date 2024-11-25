package app.playground

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

actual fun testDriver(sqlSchema: SqlSchema<QueryResult.Value<Unit>>): SqlDriver {
  val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
  sqlSchema.create(driver)
  return driver
}
