package app.playground.frontend.modal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import app.playground.frontend.PlaygroundDependencies
import app.playground.frontend.RouteModal
import app.playground.frontend.main.MainViewModelState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal data class ModalViewModelState(
  val foo: Int = 5,
)

internal class ModalViewModel(
  private val dependencies: PlaygroundDependencies,
) : ViewModel() {
  private val _state = MutableStateFlow(ModalViewModelState())
  val state = _state.asStateFlow()
}

@Composable internal fun ModalScreen(
  dependencies: PlaygroundDependencies,
  onGoBack: () -> Unit,
  route: RouteModal,
  viewModel: ModalViewModel = viewModel { ModalViewModel(dependencies) },
) {
  val state by viewModel.state.collectAsStateWithLifecycle()

  Column(Modifier.padding(16.dp)) {
    Text("${state.foo} ${route.id}")

    Button(
      onClick = onGoBack,
    ) {
      Text("Go back")
    }
  }
}
