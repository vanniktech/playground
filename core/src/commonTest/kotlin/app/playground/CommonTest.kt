package app.playground

import okio.FileSystem

expect fun fileSystem(): FileSystem
expect fun getEnv(name: String): String?
