import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode

plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.multiplatform")
  id("org.jetbrains.kotlin.native.cocoapods")
  id("org.jetbrains.kotlin.plugin.parcelize")
  id("org.jetbrains.kotlin.plugin.serialization")
  id("app.cash.sqldelight")
}

sqldelight {
  databases {
    create("QueryWrapper") {
      packageName.set("com.vanniktech.playground.kmp")
      schemaOutputDirectory.set(file("src/commonMain/sqldelight/databases"))
      verifyMigrations.set(true)
      generateAsync.set(true)
    }
  }
}

kotlin {
  applyDefaultHierarchyTemplate()

  androidTarget()
  jvm()
  jvmToolchain(11)
  iosX64()
  iosArm64()
  iosSimulatorArm64()

  sourceSets {
    val commonMain by getting {
      dependencies {
        api(libs.kotlinx.coroutines)
        api(libs.kotlinx.datetime)
        api(libs.kotlinx.serialization.json)
        api(libs.ktor.client.core)
        api(libs.ktor.client.logging)
        api(libs.multiplatform.settings)
        api(libs.multiplatform.settings.serialization)
        api(libs.okio)
        api(libs.picnic)
        api(libs.uuid)
        api(libs.sqldelight.primitive.adapters)
        api(libs.sqldelight.async.extensions)
      }
    }

    val commonTest by getting {
      dependencies {
        implementation(libs.kotlin.reflect)
        implementation(libs.kotlin.test.annotations.common)
        implementation(libs.kotlin.test.common)
        implementation(libs.multiplatform.settings.test)
      }
    }

    val androidMain by getting {
      dependencies {
        api(libs.ktor.client.okhttp)
        api(libs.sqldelight.android.driver)
      }
    }

    val androidUnitTest by getting {
      dependencies {
        implementation(libs.kotlin.test.junit)
      }
    }

    val jvmMain by getting {
      dependencies {
        api(libs.ktor.client.okhttp)
        api(libs.sqldelight.sqlite.driver)
      }
    }

    val jvmTest by getting {
      dependencies {
        implementation(libs.kotlin.test.junit)
      }
    }

    val iosMain by getting {
      dependencies {
        api(libs.crashkios)
        api(libs.ktor.client.darwin)
        api(libs.sqldelight.native.driver)
      }
    }
  }

  cocoapods {
    framework {
      isStatic = true
      embedBitcode(if ("YES" == System.getenv("ENABLE_BITCODE")) BitcodeEmbeddingMode.BITCODE else BitcodeEmbeddingMode.DISABLE)
    }

    summary = "Shared"
    homepage = "https://www.vanniktech.de/"
    version = "1.0.0"
  }
}

android {
  namespace = "com.vanniktech.playground.kmp"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = libs.versions.minSdk.get().toInt()
  }

  buildFeatures {
    viewBinding = true
    buildConfig = false
  }

  compileOptions {
    isCoreLibraryDesugaringEnabled = true
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  packaging {
    resources.excludes.add("META-INF/*.kotlin_module")
  }
}

dependencies {
  coreLibraryDesugaring(libs.desugar.jdk.libs)
}
