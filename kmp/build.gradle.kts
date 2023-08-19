import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode

plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.multiplatform")
  id("org.jetbrains.kotlin.native.cocoapods")
  id("org.jetbrains.kotlin.plugin.parcelize")
  id("org.jetbrains.kotlin.plugin.serialization")
  id("com.google.devtools.ksp")
  id("com.rickclephas.kmp.nativecoroutines")
}

kotlin {
  androidTarget()
  jvm()
  jvmToolchain {
    languageVersion.set(JavaLanguageVersion.of("11"))
  }
  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64(),
  ).forEach {
    it.binaries.all {
      if (this is org.jetbrains.kotlin.gradle.plugin.mpp.Framework) {
        isStatic = true
        embedBitcode(if ("YES" == System.getenv("ENABLE_BITCODE")) BitcodeEmbeddingMode.BITCODE else BitcodeEmbeddingMode.DISABLE)
      }
    }
  }

  sourceSets {
    val commonMain by getting {
      dependencies {
        api(libs.kotlinx.datetime)
        api(libs.kotlinx.serialization.json)
        api(libs.multiplatform.settings)
        api(libs.multiplatform.settings.serialization)
        api(libs.uuid)
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
      }
    }

    val androidUnitTest by getting {
      dependencies {
        implementation(libs.kotlin.test.junit)
      }
    }

    val jvmTest by getting {
      dependencies {
        implementation(libs.kotlin.test.junit)
      }
    }

    val iosX64Main by getting
    val iosArm64Main by getting
    val iosSimulatorArm64Main by getting
    val iosMain by creating {
      dependsOn(commonMain)
      iosX64Main.dependsOn(this)
      iosArm64Main.dependsOn(this)
      iosSimulatorArm64Main.dependsOn(this)

      dependencies {
        api(libs.crashkios)
      }
    }

    val iosX64Test by getting
    val iosArm64Test by getting
    val iosSimulatorArm64Test by getting
    val iosTest by creating {
      dependsOn(commonTest)
      iosX64Test.dependsOn(this)
      iosArm64Test.dependsOn(this)
      iosSimulatorArm64Test.dependsOn(this)
    }
  }

  cocoapods {
    framework {
      isStatic = true
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
