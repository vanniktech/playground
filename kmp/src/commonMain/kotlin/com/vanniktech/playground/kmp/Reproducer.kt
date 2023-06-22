package com.vanniktech.playground.kmp

expect class LatLng(
  latitude: Double,
  longitude: Double,
) : Parcelable {
  val latitude: Double
  val longitude: Double
}

expect interface Parcelable

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
expect annotation class Parcelize()
