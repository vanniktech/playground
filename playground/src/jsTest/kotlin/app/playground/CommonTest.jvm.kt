package app.playground

import okio.FileSystem

actual fun fileSystem(): FileSystem = error("Not yet supported.") // TODO(nik)
actual fun getEnv(name: String): String? = null // TODO(nik)
