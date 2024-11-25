package app.playground

import kotlinx.serialization.Serializable

sealed interface DeepLink
@Serializable data object DeepLinkMain : DeepLink
