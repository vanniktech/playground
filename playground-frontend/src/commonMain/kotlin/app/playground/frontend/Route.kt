package app.playground.frontend

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Serializable
internal data object RouteMain : NavKey

@Serializable
internal data class RouteModal(val id: String) : NavKey

internal val NavKeyConfig = SavedStateConfiguration {
  serializersModule = SerializersModule {
    polymorphic(NavKey::class) {
      subclass(RouteMain::class, RouteMain.serializer())
      subclass(RouteModal::class, RouteModal.serializer())
    }
  }
}
