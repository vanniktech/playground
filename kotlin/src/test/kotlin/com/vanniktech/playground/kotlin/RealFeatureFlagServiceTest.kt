package com.vanniktech.playground.kotlin

import com.vanniktech.playground.kmp.AppVersion
import com.vanniktech.playground.kmp.DefaultFeatureFlag
import com.vanniktech.playground.kmp.RealFeatureFlagService
import kotlin.test.Test
import kotlin.test.assertEquals

class RealFeatureFlagServiceTest {
  @Test fun isEnabled() {
    assertEquals(
      expected = listOf(
        FeatureFlagConfiguration(featureFlag = DefaultFeatureFlag.AVATAR, enabled = false),
      ),
      actual = DefaultFeatureFlag.values()
        .map {
          FeatureFlagConfiguration(
            featureFlag = it,
            enabled = RealFeatureFlagService(AppVersion.last()).isEnabled(it),
          )
        },
    )
  }

  data class FeatureFlagConfiguration(
    private val featureFlag: DefaultFeatureFlag,
    private val enabled: Boolean,
  )

  @Test fun explicitlyDisabled() {
    assertEquals(
      expected = false,
      actual = RealFeatureFlagService(appVersion = AppVersion.V0_52_0).isEnabled(automaticallyTurnedOnFor = AppVersion.V0_53_0.version),
    )
  }
}
