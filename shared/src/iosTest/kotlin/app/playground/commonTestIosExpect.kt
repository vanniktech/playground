package app.playground

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toKString
import okio.FileSystem
import platform.posix.getenv

actual fun fileSystem() = FileSystem.SYSTEM

@OptIn(ExperimentalForeignApi::class)
actual fun getEnv(name: String): String? =
  getenv(name)?.toKString()
