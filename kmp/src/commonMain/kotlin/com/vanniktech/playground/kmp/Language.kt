package com.vanniktech.playground.kmp

sealed interface Language

// /Users/niklas/dev/GitHub/playground/kmp/src/commonMain/kotlin/com/vanniktech/playground/kmp/Language.kt: (6, 24): 'when' expression must be exhaustive, add necessary 'else' branch
fun Language.hello() = when (this) {
  is German -> "Hallo"
  is English -> "Hello"
}
