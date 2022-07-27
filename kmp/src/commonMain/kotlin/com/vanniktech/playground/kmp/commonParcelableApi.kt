package com.vanniktech.playground.kmp

expect interface Parcelable

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
expect annotation class Parcelize()

// The error is here!
// Declaration annotated with '@OptionalExpectation' can only be used in common module sources
@Parcelize
data class Something(
  val foo: Int,
) : Parcelable
