package app.playground

@RequiresOptIn(level = RequiresOptIn.Level.ERROR)
@Retention(AnnotationRetention.BINARY)
annotation class UnsupportedAppVersion

annotation class UnusedSince(val version: AppVersion)

enum class AppVersion {
  @UnsupportedAppVersion V0_1_0,
  @UnsupportedAppVersion V0_2_0,
  V0_3_0,
}

@OptIn(UnsupportedAppVersion::class) fun something(appVersion: AppVersion): String {
  if (appVersion <= AppVersion.V0_2_0) { // This correctly produces a compiler error.
    return "something special"
  }

  return "foo"
}
