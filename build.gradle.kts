import com.android.build.gradle.api.AndroidBasePlugin
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  alias(libs.plugins.androidApplication) apply false
  alias(libs.plugins.androidLibrary) apply false
  alias(libs.plugins.cachefix) apply false
  alias(libs.plugins.codequalitytools)
  alias(libs.plugins.compose.compiler) apply false
  alias(libs.plugins.jetbrainsCompose) apply false
  alias(libs.plugins.kotlinJvm) apply false
  alias(libs.plugins.kotlinMultiplatform) apply false
  alias(libs.plugins.kotlinParcelize) apply false
  alias(libs.plugins.kotlinSerialization) apply false
  alias(libs.plugins.shadow) apply false
  alias(libs.plugins.sqldelight) apply false
}

codeQualityTools {
  checkstyle {
    enabled = false // Kotlin only.
  }
  pmd {
    enabled = false // Kotlin only.
  }
  ktlint {
    toolVersion = libs.versions.ktlint.get()
  }
  detekt {
    enabled = false // Don"t want.
  }
  cpd {
    enabled = false // Kotlin only.
  }
  lint {
    lintConfig = rootProject.file("lint.xml")
    checkAllWarnings = true
  }
  kotlin {
    allWarningsAsErrors = true
  }
}

subprojects {
  plugins.withType<AndroidBasePlugin> {
    apply(plugin = "org.gradle.android.cache-fix")
  }

  tasks.withType<Test>().configureEach {
    testLogging.exceptionFormat = TestExceptionFormat.FULL
  }

  tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
      freeCompilerArgs.addAll(
        "-Xconsistent-data-class-copy-visibility",
      )
    }
  }

  plugins.withId("org.jetbrains.kotlin.plugin.parcelize") {
    tasks.withType<KotlinCompile>().configureEach {
      compilerOptions {
        freeCompilerArgs.addAll(
          "-P",
          "plugin:org.jetbrains.kotlin.parcelize:additionalAnnotation=app.playground.Parcelize",
        )
      }
    }
  }
}
