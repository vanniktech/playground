import com.android.build.gradle.tasks.factory.AndroidUnitTest
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.testing.KotlinJsTest
import org.jetbrains.kotlin.gradle.targets.jvm.tasks.KotlinJvmTest
import org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeTest

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.kotlinSerialization)
  alias(libs.plugins.androidLibrary)
  alias(libs.plugins.kotlinParcelize)
}

kotlin {
  // @OptIn(ExperimentalWasmDsl::class)
  // wasmJs {
  //   browser {
  //     val projectDirPath = project.projectDir.path
  //     commonWebpackConfig {
  //       devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
  //         static = (static ?: mutableListOf()).apply {
  //           // Serve sources to debug inside browser.
  //           add(projectDirPath)
  //         }
  //       }
  //     }
  //   }
  // }

  androidTarget {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
      jvmTarget.set(JvmTarget.JVM_11)
    }
  }

  iosX64()
  iosArm64()
  iosSimulatorArm64()

  jvm()

  sourceSets {
    commonMain.dependencies {
      api(libs.kotlinx.coroutines.core)
      api(libs.kotlinx.datetime)
      api(libs.kotlinx.serialization.json)
      api(libs.ktor.http)
      api(libs.ktor.serialization.kotlinx.json)
      api(libs.multiplatform.locale)
      api(libs.okio)
      api(libs.sqldelight.runtime)
      api(libs.uuid)
    }

    androidMain.dependencies {
      api(libs.kotlinx.coroutines.android)
    }

    val commonTest by getting
    commonTest.dependencies {
      implementation(libs.kotlin.test.annotations.common)
      implementation(libs.kotlin.test.common)
      implementation(libs.kotlinx.coroutines.test)
      implementation(libs.ksoup.okio)
      implementation(libs.multiplatform.settings.test)
      implementation(libs.okio.fakefilesystem)
    }

    val iosTest by creating
    iosTest.dependencies {
      implementation(libs.sqldelight.native.driver)
    }

    val jvmTest by getting
    jvmTest.dependencies {
      implementation(libs.kotlin.test.junit)
      implementation(libs.sqldelight.sqlite.driver)
    }

    val androidUnitTest by getting
    androidUnitTest.dependencies {
      implementation(libs.kotlin.test.junit)
      implementation(libs.sqldelight.sqlite.driver)
    }
  }
}

android {
  namespace = "app.playground.shared"
  compileSdk = libs.versions.android.compileSdk.get().toInt()

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  defaultConfig {
    minSdk = libs.versions.android.minSdk.get().toInt()
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
