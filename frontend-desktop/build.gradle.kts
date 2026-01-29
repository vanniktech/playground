import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.compose.multiplatform)
  alias(libs.plugins.kotlin.jvm)
}

kotlin {
  jvmToolchain(21)
}

dependencies {
  implementation(projects.frontend)
}

compose.desktop {
  application {
    mainClass = "app.playground.desktop.MainKt"

    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = "app.playground.desktop"
      packageVersion = "1.0.0"
    }
  }
}
