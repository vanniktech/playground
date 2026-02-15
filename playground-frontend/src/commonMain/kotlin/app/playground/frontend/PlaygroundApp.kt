package app.playground.frontend

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onVisibilityChangedNode
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.benasher44.uuid.uuid4

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun PlaygroundApp(
  playgroundDependencies: PlaygroundDependencies,
) = Theme {
  initCoil(playgroundDependencies)
  val backStack = rememberNavBackStack(NavKeyConfig, RouteMain)

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
  ) { innerPadding ->
    NavDisplay(
      backStack = backStack,
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding),
      entryProvider = entryProvider {
        entry<RouteMain> {
          Column(Modifier.padding(16.dp)) {
            Text("Hello World")

            Button(
              onClick = { backStack.add(RouteId(uuid4().toString())) },
            ) {
              Text("Navigate")
            }
          }
        }

        entry<RouteId> { route ->
          Column(Modifier.padding(16.dp)) {
            Text(route.id)

            Button(
              onClick = { backStack.removeLastOrNull() },
            ) {
              Text("Go back")
            }
          }
        }
      },
    )
  }
}
