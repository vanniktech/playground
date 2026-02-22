package app.playground.frontend

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import app.playground.frontend.main.entryProviderMain
import app.playground.frontend.modal.entryProviderModal

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun PlaygroundApp(
  dependencies: PlaygroundDependencies,
) = Theme {
  initCoil(dependencies)
  val backStack = rememberNavBackStack(NavKeyConfig, RouteMain)
  val currentRoute = backStack.lastOrNull()

  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Column {
            Text(text = "Playground")
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
      )
    },
    floatingActionButton = {
      when (currentRoute) {
        is RouteMain -> ExtendedFloatingActionButton(onClick = { }) {
          Text("Foo")
        }
        else -> Unit
      }
    }
  ) { innerPadding ->
    NavDisplay(
      backStack = backStack,
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding),
      entryDecorators = listOf(
        rememberSaveableStateHolderNavEntryDecorator(),
        rememberViewModelStoreNavEntryDecorator(),
      ),
      entryProvider = entryProvider {
        entryProviderMain(
          dependencies = dependencies,
          onNavigate = backStack::add,
        )
        entryProviderModal(
          dependencies = dependencies,
          onGoBack = backStack::removeLastOrNull,
        )
      },
    )
  }
}
