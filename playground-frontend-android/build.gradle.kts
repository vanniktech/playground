plugins {
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.android.application)
}

android {
  namespace = "app.playground.android"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = libs.versions.minSdk.get().toInt()
    targetSdk = libs.versions.targetSdk.get().toInt()

    applicationId = "app.playground.android"
    versionCode = 1
    versionName = "1.0.0"
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
}

kotlin {
  jvmToolchain(17)
}

dependencies {
  implementation(project(":playground-frontend"))
}
