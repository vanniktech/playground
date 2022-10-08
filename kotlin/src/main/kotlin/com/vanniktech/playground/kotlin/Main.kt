package com.vanniktech.playground.kotlin

fun main() {
  print("Hello World")

  foo(
    a = {
      foo(
        a = {},
      )
    }
  )
}

fun foo(a: () -> Unit) {

}
