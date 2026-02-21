import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

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
      packageName.set("app.playground.frontend.database")
      schemaOutputDirectory.set(project.file("src/commonMain/sqldelight/databases"))
      verifyMigrations.set(true)
    }
  }
}

compose.resources {
  publicResClass = true
  packageOfResClass = "app.playgorund.frontend"
  generateResClass = always
  nameOfResClass = "Res"
}

kotlin {
  androidLibrary {
    namespace = "app.playground.frontend"
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

  js {
    browser()
    binaries.executable()
    useCommonJs()
  }
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
      api(project(":playground"))

      implementation(libs.androidx.lifecycle.runtime.compose)
      implementation(libs.androidx.lifecycle.viewmodel.compose)
      implementation(libs.androidx.lifecycle.viewmodel.navigation3)
      implementation(libs.androidx.navigation3.ui)
      implementation(libs.coil.compose)
      implementation(libs.coil.network)
      implementation(libs.compose.material3.adaptive.navigation)
      implementation(libs.kotlinx.collections.immutable)
      implementation(libs.ktor.client.auth)
      implementation(libs.ktor.client.callId)
      implementation(libs.ktor.client.content.negotiation)
      implementation(libs.ktor.client.core)
      implementation(libs.ktor.client.logging)
      implementation(libs.sqldelight.coroutines)
      implementation(libs.sqldelight.primitive.adapters)
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
  }

  targets
    .withType<KotlinNativeTarget>()
    .matching { it.konanTarget.family.isAppleFamily }
    .configureEach {
      binaries {
        framework {
          baseName = "playgroundfrontend"
          isStatic = true
        }
      }
    }
}
