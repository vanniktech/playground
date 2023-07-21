plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
  android()
  jvm()
  jvmToolchain {
    languageVersion.set(JavaLanguageVersion.of(11))
  }
  iosX64()
  iosArm64()
  iosSimulatorArm64()

  sourceSets {
    val commonMain by getting {
      dependencies {
        api(libs.kotlin.test.common)
        api(libs.kotlinx.datetime)
        api(libs.multiplatform.settings.test)
      }
    }

    val commonTest by getting {
    }

    val androidMain by getting {
      dependencies {
        api(libs.kotlin.test.junit)
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
}

android {
  namespace = "com.vanniktech.testing.kmp"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    vectorDrawables.useSupportLibrary = true
    minSdk = libs.versions.minSdk.get().toInt()
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildFeatures {
    viewBinding = false
    buildConfig = false
  }

  compileOptions {
    isCoreLibraryDesugaringEnabled = true
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  resourcePrefix = "testing_kmp_"

  packaging {
    resources.excludes.add("META-INF/*.kotlin_module")
  }
}

dependencies {
  coreLibraryDesugaring(libs.desugar.jdk.libs)
}
