package app.playground.frontend.modal

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import app.playground.frontend.PlaygroundDependencies
import app.playground.frontend.RouteModal

fun EntryProviderScope<NavKey>.entryProviderModal(
  dependencies: PlaygroundDependencies,
  onGoBack: () -> Unit,
) {
  entry<RouteModal> { route ->
    ModalScreen(
      dependencies = dependencies,
      onGoBack = onGoBack,
      route = route,
    )
  }
}
