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
}

dependencies {
  testImplementation(libs.kotlin.test.junit)
}
