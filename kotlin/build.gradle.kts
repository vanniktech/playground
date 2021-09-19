plugins {
  kotlin("jvm")
  kotlin("plugin.serialization")
  application
}

application {
  applicationName = "kotlin"
  mainClassName = "com.vanniktech.playground.kotlin.MainKt"
}

defaultTasks("run")

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.0")

  val ktor = "1.6.3"
  implementation("io.ktor:ktor-client-core-jvm:$ktor")
  implementation("io.ktor:ktor-client-okhttp:$ktor")

  implementation("com.jakewharton.picnic:picnic:0.5.0")
}

dependencies {
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}
