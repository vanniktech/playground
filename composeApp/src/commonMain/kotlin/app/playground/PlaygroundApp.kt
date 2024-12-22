package app.playground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable data class ScaffoldViewState(
  val toolbarState: ToolbarState?,
)

@Immutable
data class ToolbarState(
  val title: String,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun PlaygroundApp(
  frontendDependencies: FrontendDependencies,
  navHostController: NavHostController = rememberNavController(),
) {
  initCoil(frontendDependencies)

  val startDestination = DeepLinkMain
  val scaffoldViewState = remember {
    mutableStateOf("")
  }

  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Column {
            Text(text = scaffoldViewState.value)
          }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
          containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
      )
    },
  ) { innerPadding ->
    NavHost(
      navController = navHostController,
      startDestination = startDestination,
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding),
    ) {
      composable<DeepLinkMain> {
        LaunchedEffect(scaffoldViewState) {
          scaffoldViewState.value = "Main"
        }

        Column {
          Button(onClick = { navHostController.navigate(DeepLinkFoo) }) {
            Text("Navigate")
          }
        }
      }

      composable<DeepLinkFoo> {
        LaunchedEffect(scaffoldViewState) {
          scaffoldViewState.value = "Foo"
        }

        Column {
          Text("Foo screen")
        }
      }
    }
  }
}
