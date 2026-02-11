import com.android.build.gradle.tasks.factory.AndroidUnitTest
import org.jetbrains.kotlin.gradle.targets.js.testing.KotlinJsTest
import org.jetbrains.kotlin.gradle.targets.jvm.tasks.KotlinJvmTest
import org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeTest

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.android.kotlin.muliplatform.library)
  alias(libs.plugins.kotlin.parcelize)
}

kotlin {
  androidLibrary {
    namespace = "app.playground"
    compileSdk = libs.versions.compileSdk.get().toInt()
    minSdk = libs.versions.minSdk.get().toInt()
    // https://issuetracker.google.com/issues/470478219 resourcePrefix = "feature_"
    withHostTest { }

    androidResources {
      enable = true
    }
  }

  js {
    browser()
    binaries.executable()
    useCommonJs()
  }
  iosArm64()
  iosSimulatorArm64()
  jvm()
  jvmToolchain(17)

  sourceSets {
    commonMain.dependencies {
      api(libs.kotlinx.coroutines.core)
      api(libs.kotlinx.datetime)
      api(libs.kotlinx.serialization.json)
      api(libs.ktor.http)
      api(libs.ktor.serialization.kotlinx.json)
      api(libs.okio)
      api(libs.sqldelight.runtime)
      api(libs.uuid)
    }

    androidMain.dependencies {
      api(libs.kotlinx.coroutines.android)
    }

    commonTest.dependencies {
      implementation(kotlin("test"))
      implementation(libs.kotlinx.coroutines.test)
      implementation(libs.ksoup.okio)
      implementation(libs.multiplatform.settings.test)
      implementation(libs.okio.fakefilesystem)
    }

    iosTest.dependencies {
      implementation(libs.sqldelight.native.driver)
    }

    jvmTest.dependencies {
      implementation(libs.sqldelight.sqlite.driver)
    }

    val androidHostTest by getting
    androidHostTest.dependencies {
      implementation(libs.sqldelight.sqlite.driver)
    }
  }
}

tasks.withType<AndroidUnitTest>().configureEach {
  environment("PLAYGROUND_ROOT", rootDir)
}

tasks.withType<KotlinJvmTest>().configureEach {
  environment("PLAYGROUND_ROOT", rootDir)
}

tasks.withType<KotlinNativeTest>().configureEach {
  environment("SIMCTL_CHILD_PLAYGROUND_ROOT", rootDir)
  environment("PLAYGROUND_ROOT", rootDir)
}

tasks.withType<KotlinJsTest>().configureEach {
  environment("PLAYGROUND_ROOT", rootDir.toString())
}
