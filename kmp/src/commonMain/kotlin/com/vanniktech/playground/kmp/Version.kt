package com.vanniktech.playground.kmp

data class Version(
  val major: Int,
  val minor: Int,
  val patch: Int,
) : Comparable<Version> {
  override fun compareTo(other: Version) =
    compareValuesBy(this, other, { it.major }, { it.minor }, { it.patch })

  override fun toString() = "$major.$minor.$patch"

  companion object {
    fun from(version: String) = fromOrNull(version) ?: error("Can't parse $version into Version")
    fun fromOrNull(version: String?): Version? {
      val parts = version?.split(".")?.mapNotNull { it.toIntOrNull() }.orEmpty()

      if (parts.size in 2..3) {
        return Version(
          major = parts[0],
          minor = parts[1],
          patch = parts.getOrNull(2) ?: 0,
        )
      }

      return null
    }
  }
}
