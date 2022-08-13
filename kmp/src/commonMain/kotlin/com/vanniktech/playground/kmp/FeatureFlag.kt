package com.vanniktech.playground.kmp

enum class DefaultFeatureFlag(
  val since: AppVersion,
  val automaticallyTurnedOnFor: AppVersion?,
) {
  AVATAR(
    since = AppVersion.V0_67_0,
    automaticallyTurnedOnFor = null,
  ),
  ;
}

data class FeatureFlag(
  val since: Version,
  val automaticallyTurnedOnFor: Version?,
)

fun DefaultFeatureFlag.asFeatureFlag() = FeatureFlag(
  since = since.version,
  automaticallyTurnedOnFor = automaticallyTurnedOnFor?.version,
)
