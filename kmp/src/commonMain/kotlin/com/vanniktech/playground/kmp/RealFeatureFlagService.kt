package com.vanniktech.playground.kmp

interface FeatureFlagService {
  fun isEnabled(featureFlag: DefaultFeatureFlag): Boolean
  fun isEnabled(featureFlag: FeatureFlag): Boolean
}

class RealFeatureFlagService(
  private val appVersion: AppVersion,
) : FeatureFlagService {
  override fun isEnabled(featureFlag: DefaultFeatureFlag): Boolean {
    return isEnabled(featureFlag.asFeatureFlag())
  }

  override fun isEnabled(featureFlag: FeatureFlag) = isEnabled(
    automaticallyTurnedOnFor = featureFlag.automaticallyTurnedOnFor,
  )

  fun isEnabled(automaticallyTurnedOnFor: Version?): Boolean {
    return automaticallyTurnedOnFor != null && appVersion.version >= automaticallyTurnedOnFor
  }
}
