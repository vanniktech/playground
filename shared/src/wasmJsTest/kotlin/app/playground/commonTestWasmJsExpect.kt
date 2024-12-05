package app.playground

import okio.FileSystem

actual fun fileSystem(): FileSystem = error("Needs to be implemented")

actual fun getEnv(name: String): String? = error("Needs to be implemented")
