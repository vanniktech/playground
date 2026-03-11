package app.playground.frontend.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import app.playground.frontend.PlaygroundDependencies
import app.playground.frontend.RouteMain
import app.playground.frontend.RouteModal
import com.benasher44.uuid.uuid4
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class SomethingFoo(
  val id: Int,
  val text: String,
)

internal data class MainViewModelState(
  val items: List<SomethingFoo>,
)

internal class MainViewModel(
  private val dependencies: PlaygroundDependencies,
) : ViewModel() {
  private val _state = MutableStateFlow(MainViewModelState(
    items = listOf(
      SomethingFoo(id = 0, "Hello"),
      SomethingFoo(id = 1, "World"),
    )
  ))
  val state = _state.asStateFlow()
}

@Composable internal fun MainScreen(
  dependencies: PlaygroundDependencies,
  onNavigate: (NavKey) -> Unit,
  route: RouteMain,
  viewModel: MainViewModel = viewModel { MainViewModel(dependencies) },
) {
  val state by viewModel.state.collectAsStateWithLifecycle()

  Column(Modifier.padding(16.dp)) {
    LazyColumn {
      items(state.items, key = { it.id }, contentType = { "Text" }) {
        Text(it.text)
      }
    }

    Button(
      onClick = { onNavigate(RouteModal(uuid4().toString())) },
    ) {
      Text("Navigate")
    }
  }
}
