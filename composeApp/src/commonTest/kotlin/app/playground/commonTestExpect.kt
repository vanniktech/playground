package app.playground

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import kotlinx.coroutines.runBlocking

expect fun testDriver(sqlSchema: SqlSchema<QueryResult.Value<Unit>>): SqlDriver

fun <T> runCoroutine(block: suspend () -> T): T = runBlocking { block() }
