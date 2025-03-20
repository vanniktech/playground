package app.playground

import kotlinx.serialization.Serializable

sealed interface DeepLink
@Serializable data object DeepLinkMain : DeepLink
@Serializable data object DeepLinkOnboarding : DeepLink

enum class DeepLinkOnboardingStep {
  NAME,
  BIRTHDAY,
}
