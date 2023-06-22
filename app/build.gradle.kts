plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
}

kotlin {
  jvmToolchain {
    languageVersion.set(JavaLanguageVersion.of("11"))
  }
}

android {
  namespace = "com.vanniktech.playground"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    applicationId = "com.vanniktech.playground"
    minSdk = libs.versions.minSdk.get().toInt()
    targetSdk = libs.versions.targetSdk.get().toInt()
    versionCode = 1
    versionName = "1.0.0"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildFeatures {
    viewBinding = true
  }

  compileOptions {
    isCoreLibraryDesugaringEnabled = true
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
}

dependencies {
  coreLibraryDesugaring(libs.desugar.jdk.libs)
}

dependencies {
  implementation(project(":kmp"))
  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.annotation)
  implementation(libs.androidx.constraintlayout)
  implementation(libs.material)
  implementation(libs.rxjava)
  implementation(libs.rxandroid)
  implementation(libs.lottie)
  implementation(libs.timber)
  implementation(libs.adapterdelegates4.kotlin.dsl)
}

dependencies {
  testImplementation(libs.kotlin.test.junit)
}

dependencies {
  androidTestImplementation(libs.androidx.test.junit)
  androidTestImplementation(libs.androidx.test.rules)
  androidTestImplementation(libs.androidx.test.runner)
  androidTestImplementation(libs.androidx.espresso.contrib)
}
