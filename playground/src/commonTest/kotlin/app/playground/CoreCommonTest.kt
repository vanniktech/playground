package app.playground

import okio.Path.Companion.toPath
import okio.buffer
import kotlin.test.Test
import kotlin.test.assertEquals

class CoreCommonTest {
  @Test fun math() {
    assertEquals(expected = 4, actual = 4)
  }

  @Test fun readFile() {
    // TODO(nik) JS is missing here.
    val path = getEnv("PLAYGROUND_ROOT")?.toPath()?.div("settings.gradle.kts") ?: return
    val fileSystem = runCatching { fileSystem() }.getOrNull() ?: return

    assertEquals(
      expected = "pluginManagement {",
      actual = fileSystem.source(path).buffer().readUtf8().lines().first(),
    )
  }
}
