package app.playground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun PlaygroundApp(
  frontendDependencies: FrontendDependencies,
  navHostController: NavHostController = rememberNavController(),
) {
  initCoil(frontendDependencies)

  val startDestination = DeepLinkMain

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
    NavHost(
      navController = navHostController,
      startDestination = startDestination,
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding),
    ) {
      composable<DeepLinkMain> {
        MainScreen(
          navHostController = navHostController,
        )
      }
    }
  }
}
