package app.playground.frontend.modal

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import app.playground.frontend.PlaygroundDependencies
import app.playground.frontend.RouteModal

fun EntryProviderScope<NavKey>.entryProviderModal(
  dependencies: PlaygroundDependencies,
  onGoBack: () -> Unit,
) {
  entry<RouteModal>(
    metadata = NavDisplay.transitionSpec {
      slideInVertically(initialOffsetY = { it }) togetherWith
        slideOutVertically(targetOffsetY = { -it })
    } + NavDisplay.popTransitionSpec {
      EnterTransition.None togetherWith
        slideOutVertically(targetOffsetY = { it })
    } + NavDisplay.predictivePopTransitionSpec {
      EnterTransition.None togetherWith
        slideOutVertically(targetOffsetY = { it })
    }
  ) { route ->
    ModalScreen(
      dependencies = dependencies,
      onGoBack = onGoBack,
      route = route,
    )
  }
}
