package app.playground.server

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.todayIn
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.CoreErrorCode
import org.flywaydb.core.api.FlywayException

fun main(args: Array<String>) {
  val clock = Clock.System

  val lastDay = LocalDate(2025, Month.FEBRUARY, 25)
  val today = clock.todayIn(TimeZone.currentSystemDefault())
  val date = LocalDate(2025, Month.MAY, 6)

  val total = lastDay.daysUntil(date)
  val passed = lastDay.daysUntil(today)
  val remaining = total - passed
  val percentage = (passed / total.toDouble()) * 100
  println("$passed / $total -> $remaining ($percentage%)")

  // val dataSource = HikariDataSource(
  //   HikariConfig().apply {
  //     maximumPoolSize = 4
  //     jdbcUrl = "jdbc:postgresql://localhost:5432/playground"
  //     username = "playground"
  //     password = "playground"
  //     isAutoCommit = true
  //   },
  // )
  //
  // val flyway = Flyway.configure()
  //   .baselineOnMigrate(true)
  //   .baselineVersion("0")
  //   .dataSource(dataSource)
  //   .failOnMissingLocations(true)
  //   .locations("classpath:db/migration")
  //   .validateMigrationNaming(true)
  //   .validateOnMigrate(true)
  //   .cleanDisabled(false)
  //   .load()
  //
  // val runFaker = try {
  //   flyway.migrate()
  // } catch (flywayException: FlywayException) {
  //   if (flywayException.errorCode == CoreErrorCode.VALIDATE_ERROR) {
  //     // Recreate everything for easier development.
  //     flyway.clean()
  //     flyway.migrate()
  //     true
  //   } else {
  //     throw flywayException
  //   }
  // }
  //
  // val dependencies = ServerDependencies(
  //   dataSource = dataSource,
  // )
  //
  // embeddedServer(
  //   factory = Netty,
  //   port = 8080,
  //   host = "0.0.0.0",
  //   module = {
  //     install(ContentNegotiation) {
  //       json(dependencies.json)
  //     }
  //
  //     routing {
  //       get("/") {
  //         call.respond("Hello World!")
  //       }
  //     }
  //   },
  // )
  //   .start(wait = true)
}
