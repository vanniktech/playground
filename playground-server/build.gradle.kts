import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.ktor)
  alias(libs.plugins.shadow)
  alias(libs.plugins.sqldelight)
  application
}

sqldelight {
  databases {
    register("QueryWrapper") {
      deriveSchemaFromMigrations.set(true)
      dialect(libs.sqldelight.postgresql.dialect)
      migrationOutputDirectory = layout.buildDirectory.dir("resources/main/db/migration")
      migrationOutputFileFormat.set(".sql")
      packageName.set("app.playground.server.database")
    }
  }
}

group = "app.playground.server"
version = "1.0.0"

kotlin {
  jvmToolchain(21)
}

application {
  mainClass.set("app.playground.server.ApplicationKt")
  applicationDefaultJvmArgs = listOf(
    "-Dio.ktor.development=true",
    "-Denvironment=local",
  )
}

dependencies {
  implementation(libs.blurhash)
  implementation(libs.commons.logging)
  implementation(libs.csv)
  implementation(libs.flyway)
  implementation(libs.hikari)
  implementation(libs.ktor.server.auth)
  implementation(libs.ktor.server.call.id)
  implementation(libs.ktor.server.call.logging)
  implementation(libs.ktor.server.content.negotiation)
  implementation(libs.ktor.server.core)
  implementation(libs.ktor.server.netty)
  implementation(libs.ktor.server.status.pages)
  implementation(libs.ktor.server.websockets)
  implementation(libs.logback)
  implementation(libs.postgresql)
  implementation(libs.spring.security.crypto)
  implementation(libs.sqldelight.coroutines)
  implementation(libs.sqldelight.jdbc.driver)
  implementation(libs.sqldelight.primitive.adapters)
  implementation(libs.sqldelight.runtime)
  implementation(project(":playground"))
}

dependencies {
  testImplementation(kotlin("test"))
  testImplementation(libs.ktor.server.test.host)
}

tasks.withType<KotlinCompile>().configureEach {
  dependsOn("generateMainQueryWrapperMigrations")
}

// Use this task to generate a fat jar.
tasks.named<ShadowJar>("shadowJar") {
  archiveBaseName.set("${project.name}-all")

  // Required in order for Flyway to work correctly.
  // https://stackoverflow.com/a/77237118/1979703
  // https://github.com/Skarpton/flyway-docker-problem/commit/ef7046e1d3d6e88c8724cfe774bdf97e747dc613
  mergeServiceFiles()
}
