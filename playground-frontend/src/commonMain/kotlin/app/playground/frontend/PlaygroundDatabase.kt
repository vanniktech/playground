package app.playground.frontend

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.playground.frontend.database.QueryWrapper

const val DATABASE_NAME = "Playground.db"

fun createQueryWrapper(driver: SqlDriver): QueryWrapper {
  val queryWrapper = QueryWrapper(
    driver = driver,
  )
  driver.execute(null, "PRAGMA foreign_keys=ON", 0)
  return queryWrapper
}

private const val VERSION_PRAGMA = "user_version"

fun migrateIfNeeded(
  driver: SqlDriver,
) {
  val oldVersion = driver.executeQuery(
    identifier = null,
    sql = "PRAGMA $VERSION_PRAGMA",
    mapper = { cursor ->
      val version = when {
        cursor.next().value -> cursor.getLong(0)?.toInt()
        else -> null
      }
      QueryResult.Value(version ?: 0)
    },
    parameters = 0,
  ).value.toLong()

  val newVersion = QueryWrapper.Schema.version

  if (oldVersion == 0L) {
    println("Database: Creating DB version $newVersion!")
    QueryWrapper.Schema.create(driver)
    driver.execute(null, "PRAGMA $VERSION_PRAGMA=$newVersion", 0)
  } else if (oldVersion < newVersion) {
    println("Database: Migrating DB from version $oldVersion to $newVersion!")
    QueryWrapper.Schema.migrate(driver, oldVersion, newVersion)
    driver.execute(null, "PRAGMA $VERSION_PRAGMA=$newVersion", 0)
  }
}
