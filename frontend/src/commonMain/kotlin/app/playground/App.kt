package app.playground

import androidx.compose.runtime.Composable

@Composable fun App(
  frontendDependencies: FrontendDependencies,
) = Theme {
  PlaygroundApp(
    frontendDependencies = frontendDependencies,
  )
}
