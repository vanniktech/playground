package app.playground

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable fun App(
  frontendDependencies: FrontendDependencies,
) = Theme {
  PlaygroundApp(
    frontendDependencies = frontendDependencies,
  )
}

class MyDto(
  val property1: Int,
  @UnusedSince(AppVersion.V0_2_0) val property2: Int, // This is only marked in the IDE, but not during a Gradle build.
)
