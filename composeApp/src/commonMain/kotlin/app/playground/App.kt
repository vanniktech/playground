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
