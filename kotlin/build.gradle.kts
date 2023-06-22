plugins {
  application
  id("org.jetbrains.kotlin.jvm")
  id("org.jetbrains.kotlin.plugin.serialization")
}

kotlin {
  jvmToolchain {
    languageVersion.set(JavaLanguageVersion.of("11"))
  }
}

application {
  mainClass.set("com.vanniktech.playground.kotlin.MainKt")
}

dependencies {
  implementation(project(":kmp"))
  implementation(libs.kotlinx.serialization.json)
  implementation(libs.ktor.client.core.jvm)
  implementation(libs.ktor.client.okhttp)
  implementation(libs.picnic)
  implementation(libs.okio)
}

dependencies {
  testImplementation(libs.kotlin.test.junit)
}
