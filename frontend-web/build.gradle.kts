import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.compose.multiplatform)
}

kotlin {
  js {
    browser()
    binaries.executable()
  }

  sourceSets {
    commonMain.dependencies {
      implementation(projects.frontend)
    }

    jsMain.dependencies {
      implementation(libs.sqldelight.web.worker.driver)

      implementation(npm("@cashapp/sqldelight-sqljs-worker", libs.versions.sqldelight.get()))
      implementation(npm("sql.js", "1.8.0"))
      implementation(devNpm("copy-webpack-plugin", "9.1.0"))
    }
  }
}

tasks.withType(KotlinNpmInstallTask::class.java).configureEach {
  args.addAll(listOf("--mutex", "file:${file("../build/.yarn-mutex")}"))
}
