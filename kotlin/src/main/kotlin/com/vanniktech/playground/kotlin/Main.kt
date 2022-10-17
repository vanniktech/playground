package com.vanniktech.playground.kotlin

fun main() {
  val car = Car(4)
  echo(car)
}

fun echo(car: Car) {
  print(car)
}

data class Car(val wheels: Int)
