package app.playground

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable internal fun MainScreen(
  navHostController: NavHostController,
) = Column {
  Text("Hello World")
}
