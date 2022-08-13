package com.vanniktech.playground.kmp

import com.vanniktech.playground.kmp.DefaultFeatureFlag.AVATAR

data class Changelog internal constructor(
  val requiredFeatureFlag: DefaultFeatureFlag?,
)

enum class AppVersion(
  val changelog: List<Changelog> = emptyList(),
) {
  V0_52_0,
  V0_53_0,
  V0_67_0(
    changelog = listOf(
      Changelog(
        requiredFeatureFlag = AVATAR,
      )
    ),
  ),
  ;

  val version = Version.from(name.removePrefix("V").replace("_", "."))

  override fun toString() = version.toString()

  companion object {
    fun last() = values().last()
  }
}
