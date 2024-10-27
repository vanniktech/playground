plugins {
  application
  id("org.jetbrains.kotlin.jvm")
  id("org.jetbrains.kotlin.plugin.serialization")
}

kotlin {
  jvmToolchain(11)
}

application {
  mainClass.set("com.vanniktech.playground.kotlin.MainKt")
}

dependencies {
  implementation(project(":kmp"))
  implementation(libs.ktor.server.core)
  implementation(libs.ktor.server.auth)
  implementation(libs.ktor.server.netty)
  implementation(libs.ktor.server.status.pages)
}

dependencies {
  testImplementation(libs.kotlin.test.junit)
}
