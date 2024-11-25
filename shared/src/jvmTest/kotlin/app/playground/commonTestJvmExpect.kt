package app.playground

import okio.FileSystem

actual fun fileSystem() = FileSystem.SYSTEM
actual fun getEnv(name: String): String? =
  System.getenv(name)
