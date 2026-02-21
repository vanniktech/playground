package app.playground.frontend.main

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import app.playground.frontend.PlaygroundDependencies
import app.playground.frontend.RouteMain

fun EntryProviderScope<NavKey>.entryProviderMain(
  dependencies: PlaygroundDependencies,
  onNavigate: (NavKey) -> Unit,
) {
  entry<RouteMain> { route ->
    MainScreen(
      dependencies = dependencies,
      onNavigate = onNavigate,
      route = route,
    )
  }
}
