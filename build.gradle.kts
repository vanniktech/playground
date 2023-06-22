buildscript {
  repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
  }
  dependencies {
    classpath(libs.plugin.android.cache.fix)
    classpath(libs.plugin.androidgradleplugin)
    classpath(libs.plugin.kotlin)
    classpath(libs.plugin.kotlin.serialization)
  }
}

allprojects {
  repositories {
    mavenLocal()
    mavenCentral()
    google()
  }
}

subprojects {
  tasks.withType(Test::class.java).all {
    testLogging.exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
  }
}
