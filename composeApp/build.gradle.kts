import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.kotlinSerialization)
  alias(libs.plugins.sqldelight)
}

sqldelight {
  databases {
    create("QueryWrapper") {
      generateAsync.set(true)
      packageName.set("app.playground")
      schemaOutputDirectory.set(project.file("src/commonMain/sqldelight/databases"))
      verifyMigrations.set(true)
    }
  }
}

kotlin {
  // @OptIn(ExperimentalWasmDsl::class)
  // wasmJs {
  //   moduleName = "composeApp"
  //   browser {
  //     val projectDirPath = project.projectDir.path
  //     commonWebpackConfig {
  //       outputFileName = "composeApp.js"
  //       devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
  //         static = (static ?: mutableListOf()).apply {
  //           // Serve sources to debug inside browser
  //           add(projectDirPath)
  //         }
  //       }
  //     }
  //   }
  //   binaries.executable()
  // }

  androidTarget {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
      jvmTarget.set(JvmTarget.JVM_11)
    }
  }

  jvm("desktop")

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64(),
  ).forEach { iosTarget ->
    iosTarget.binaries.framework {
      baseName = "ComposeApp"
      isStatic = true
    }
  }

  sourceSets {
    commonMain.dependencies {
      implementation(compose.components.resources)
      implementation(compose.components.uiToolingPreview)
      implementation(compose.foundation)
      implementation(compose.material3)
      implementation(compose.runtime)
      implementation(compose.ui)
      implementation(libs.androidx.lifecycle.runtime.compose)
      implementation(libs.androidx.lifecycle.viewmodel)
      implementation(libs.androidx.navigation.compose)
      implementation(libs.blurhash)
      implementation(libs.coil.compose)
      implementation(libs.coil.network)
      implementation(libs.kotlinx.collections.immutable)
      implementation(libs.ktor.client.auth)
      implementation(libs.ktor.client.callId)
      implementation(libs.ktor.client.content.negotiation)
      implementation(libs.ktor.client.core)
      implementation(libs.ktor.client.logging)
      implementation(libs.multiplatform.settings)
      implementation(libs.sqldelight.coroutines)
      implementation(libs.sqldelight.primitive.adapters)
      implementation(projects.shared)
    }

    androidMain.dependencies {
      implementation(compose.preview)
      implementation(libs.androidx.activity.compose)
      implementation(libs.ktor.client.okhttp)
      implementation(libs.sqldelight.android.driver)
      implementation(libs.timber)
    }

    iosMain.dependencies {
      api(libs.ktor.client.darwin)
      api(libs.sqldelight.native.driver)
    }

    val desktopMain by getting
    desktopMain.dependencies {
      implementation(compose.desktop.currentOs)
      implementation(libs.kotlinx.coroutines.swing)
      implementation(libs.sqldelight.sqlite.driver)
    }

    val commonTest by getting
    commonTest.dependencies {
      implementation(libs.kotlin.test.annotations.common)
      implementation(libs.kotlin.test.common)
      implementation(libs.kotlinx.coroutines.test)
      implementation(libs.multiplatform.settings.test)
    }

    val iosTest by creating
    iosTest.dependencies {
      api(libs.sqldelight.native.driver)
    }

    val androidUnitTest by getting
    androidUnitTest.dependencies {
      implementation(libs.kotlin.test.junit)
      implementation(libs.sqldelight.sqlite.driver)
    }

    val desktopTest by getting
    desktopTest.dependencies {
      implementation(libs.kotlin.test.junit)
    }
  }
}

android {
  namespace = "app.playground.android"
  compileSdk = libs.versions.android.compileSdk.get().toInt()

  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  sourceSets["main"].res.srcDirs("src/androidMain/res")
  sourceSets["main"].resources.srcDirs("src/commonMain/resources")

  defaultConfig {
    applicationId = "app.playground.android"
    minSdk = libs.versions.android.minSdk.get().toInt()
    targetSdk = libs.versions.android.targetSdk.get().toInt()
    versionCode = libs.versions.code.get().toInt()
    versionName = libs.versions.name.get()
  }

  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      isShrinkResources = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
    }

    debug {
      applicationIdSuffix = ".dev"
      isDebuggable = true
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  buildFeatures {
    compose = true
    buildConfig = true
  }

  dependencies {
    debugImplementation(compose.uiTooling)
  }
}

compose.desktop {
  application {
    mainClass = "app.playground.desktop.MainKt"
    args(libs.versions.name.get())

    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = "app.playground.desktop"
      packageVersion = libs.versions.name.get()
    }
  }
}
