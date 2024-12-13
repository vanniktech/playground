package app.playground

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable fun App(
  frontendDependencies: FrontendDependencies,
) = Theme {
  PlaygroundApp(
    frontendDependencies = frontendDependencies,
  )
}

@Composable
private fun MyComposable() {
  val viewModel = viewModel<MyViewModel>()
  // ...
}

class MyViewModel : ViewModel()
