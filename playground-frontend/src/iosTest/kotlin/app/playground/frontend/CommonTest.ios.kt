package app.playground.frontend

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.native.inMemoryDriver

actual fun testDriver(sqlSchema: SqlSchema<QueryResult.Value<Unit>>): SqlDriver = inMemoryDriver(sqlSchema)
