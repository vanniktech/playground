@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
  alias(libs.plugins.android.kotlin.muliplatform.library)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.compose.multiplatform)
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.sqldelight)
}

sqldelight {
  databases {
    register("QueryWrapper") {
      generateAsync.set(true)
      packageName.set("app.playground")
      schemaOutputDirectory.set(project.file("src/commonMain/sqldelight/databases"))
      verifyMigrations.set(true)
    }
  }
}

kotlin {
  androidLibrary {
    namespace = "app.playground.shared"
    compileSdk = libs.versions.compileSdk.get().toInt()
    minSdk = libs.versions.minSdk.get().toInt()
    // https://issuetracker.google.com/issues/470478219 resourcePrefix = "shared_"
    withHostTest { }

    androidResources {
      enable = true
    }
  }

  jvm()
  jvmToolchain(17)

  js { browser() }
  wasmJs { browser() }
  iosX64()
  iosArm64()
  iosSimulatorArm64()

  sourceSets {
    commonMain.dependencies {
      api(libs.compose.foundation)
      api(libs.compose.material3)
      api(libs.compose.resources)
      api(libs.compose.runtime)
      api(libs.compose.ui)
      api(libs.compose.ui.tooling.preview)
      api(libs.multiplatform.settings)

      implementation(libs.androidx.lifecycle.runtime.compose)
      implementation(libs.androidx.lifecycle.viewmodel)
      implementation(libs.androidx.navigation.compose)
      implementation(libs.coil.compose)
      implementation(libs.coil.network)
      implementation(libs.kotlinx.collections.immutable)
      implementation(libs.ktor.client.auth)
      implementation(libs.ktor.client.callId)
      implementation(libs.ktor.client.content.negotiation)
      implementation(libs.ktor.client.core)
      implementation(libs.ktor.client.logging)
      implementation(libs.sqldelight.coroutines)
      implementation(libs.sqldelight.primitive.adapters)
      implementation(projects.core)
    }

    commonTest.dependencies {
      implementation(kotlin("test"))
      implementation(libs.kotlinx.coroutines.test)
      implementation(libs.multiplatform.settings.test)
    }

    androidMain.dependencies {
      api(libs.androidx.activity.compose)
      api(libs.ktor.client.okhttp)
      api(libs.sqldelight.android.driver)
      api(libs.timber)
    }

    val androidHostTest by getting
    androidHostTest.dependencies {
      implementation(libs.sqldelight.sqlite.driver)
    }

    iosMain.dependencies {
      api(libs.ktor.client.darwin)
      api(libs.sqldelight.native.driver)
    }

    iosTest.dependencies {
      api(libs.sqldelight.native.driver)
    }

    jvmMain.dependencies {
      api(libs.sqldelight.sqlite.driver)
      api(libs.sqlite.jdbc)
      implementation(compose.desktop.currentOs)
      implementation(libs.kotlinx.coroutines.swing)
    }

    jsMain.dependencies {
      api(libs.sqldelight.web.worker.driver)

      implementation(npm("@cashapp/sqldelight-sqljs-worker", libs.versions.sqldelight.get()))
      implementation(npm("sql.js", "1.10.3")) // use latest compatible version
      implementation(devNpm("copy-webpack-plugin", "11.0.0"))
    }

    wasmJsMain.dependencies {
      api(libs.sqldelight.web.worker.driver)
    }
  }
}
