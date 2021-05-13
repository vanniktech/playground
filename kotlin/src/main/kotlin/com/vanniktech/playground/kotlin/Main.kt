package com.vanniktech.playground.kotlin

import com.jakewharton.picnic.BorderStyle.Solid
import com.jakewharton.picnic.TextAlignment.MiddleCenter
import com.jakewharton.picnic.TextAlignment.MiddleRight
import com.jakewharton.picnic.table
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.random.Random

fun main(args: Array<String>) {
  val uta = Spieler("Uta")
  val nik = Spieler("Nik")

  val seed = 8
  val random = Random(seed)

  val stapelBlack = CONFIGURATION_BLACK.shuffled(random).toMutableList()
  val stapelRed = CONFIGURATION_RED.shuffled(random).toMutableList()
  val stapelBlue = CONFIGURATION_BLUE.shuffled(random).toMutableList()
  val stapelYellow = CONFIGURATION_YELLOW.shuffled(random).toMutableList()

  val spiel = Spiel(
      listOf(uta, nik),
      stapelBlack,
      stapelRed,
      stapelBlue,
      stapelYellow,
      Spielfeld(
          black = Card(64), // stapelBlack.removeFirst(),
          red1 = Card(63), // stapelRed.removeFirst(),
          red2 = Card(50), // stapelRed.removeFirst(),
          blue1 = Card(27), // stapelBlue.removeFirst(),
          blue2 = Card(42), // stapelBlue.removeFirst(),
          blue3 = Card(72), // stapelBlue.removeFirst(),
          yellow1 = Card(16), // stapelYellow.removeFirst(),
          yellow2 = Card(17), // stapelYellow.removeFirst(),
          yellow3 = Card(30), // stapelYellow.removeFirst(),
          yellow4 = Card(6), // stapelYellow.removeFirst(),
      ),
  )

  val wuerfel1 = 3 // random.nextInt(8) + 1
  val wuerfel2 = 12 // random.nextInt(10) + 1
  val wuerfel3 = 4 // random.nextInt(12) + 1

  while (true) {
    println(spiel)
    println()

    print("Cards to remove: ")
    val answers = readLine()!!.split(",").mapNotNull { it.toIntOrNull() }
    val original = spiel.spielfeld

    spiel.spielfeld = spiel.spielfeld.copy(
      black = spiel.spielfeld.black?.takeUnless { answers.contains(it.number) },
      red1 = spiel.spielfeld.red1?.takeUnless { answers.contains(it.number) },
      red2 = spiel.spielfeld.red2?.takeUnless { answers.contains(it.number) },
      blue1 = spiel.spielfeld.blue1?.takeUnless { answers.contains(it.number) },
      blue2 = spiel.spielfeld.blue2?.takeUnless { answers.contains(it.number) },
      blue3 = spiel.spielfeld.blue3?.takeUnless { answers.contains(it.number) },
      yellow1 = spiel.spielfeld.yellow1?.takeUnless { answers.contains(it.number) },
      yellow2 = spiel.spielfeld.yellow2?.takeUnless { answers.contains(it.number) },
      yellow3 = spiel.spielfeld.yellow3?.takeUnless { answers.contains(it.number) },
      yellow4 = spiel.spielfeld.yellow4?.takeUnless { answers.contains(it.number) },
    )

    print("Wuerfel 1: ")
    val wuerfel1 = readLine()!!.toInt()

    print("Wuerfel 2: ")
    val wuerfel2 = readLine()!!.toInt()

    print("Wuerfel 3: ")
    val wuerfel3 = readLine()!!.toInt()

    calculate(original, wuerfel1, wuerfel2, wuerfel3)

    print("Cards to add: ")
    val toAdd = readLine()!!.split(",").mapNotNull { it.toIntOrNull() }
    val black = CONFIGURATION_BLACK.filter { toAdd.contains(it.number) }.toMutableList()
    val red = CONFIGURATION_RED.filter { toAdd.contains(it.number) }.toMutableList()
    val blue = CONFIGURATION_BLUE.filter { toAdd.contains(it.number) }.toMutableList()
    val yellow = CONFIGURATION_YELLOW.filter { toAdd.contains(it.number) }.toMutableList()

    spiel.spielfeld = spiel.spielfeld.copy(
        black = spiel.spielfeld.black ?: black.removeFirst(),
        red1 = spiel.spielfeld.red1 ?: red.removeFirst(),
        red2 = spiel.spielfeld.red2 ?: red.removeFirst(),
        blue1 = spiel.spielfeld.blue1 ?: blue.removeFirst(),
        blue2 = spiel.spielfeld.blue2 ?: blue.removeFirst(),
        blue3 = spiel.spielfeld.blue3 ?: blue.removeFirst(),
        yellow1 = spiel.spielfeld.yellow1 ?: yellow.removeFirst(),
        yellow2 = spiel.spielfeld.yellow2 ?: yellow.removeFirst(),
        yellow3 = spiel.spielfeld.yellow3 ?: yellow.removeFirst(),
        yellow4 = spiel.spielfeld.yellow4 ?: yellow.removeFirst(),
    )
  }
}

public data class Fifth<out A, out B, out C, out D, out E>(
  public val first: A,
  public val second: B,
  public val third: C,
  public val fourth: D,
  public val fifth: E,
) {
  override fun toString(): String {
    return "$first $second $third $fourth $fifth"
  }
}

sealed class Operator {
  abstract fun calculcte(first: Double, second: Double): Double
}

object OperatorPlus : Operator() {
  override fun calculcte(first: Double, second: Double) = first + second
  override fun toString() = "+"
}

object OperatorMinus : Operator() {
  override fun calculcte(first: Double, second: Double) = first - second
  override fun toString() = "-"
}

object OperatorDividor : Operator() {
  override fun calculcte(first: Double, second: Double) = first / second
  override fun toString() = "/"
}

object OperatorMultiplier : Operator() {
  override fun calculcte(first: Double, second: Double) = first * second
  override fun toString() = "*"
}

data class Spieler(
  val name: String,
)

data class Spiel(
  private var spieler: List<Spieler>,
  private var stapelBlack: MutableList<Card>,
  private var stapelRed: MutableList<Card>,
  private var stapelBlue: MutableList<Card>,
  private var stapelYellow: MutableList<Card>,
  var spielfeld: Spielfeld = Spielfeld(
      black = stapelBlack.removeFirst(),
      red1 = stapelRed.removeFirst(),
      red2 = stapelRed.removeFirst(),
      blue1 = stapelBlue.removeFirst(),
      blue2 = stapelBlue.removeFirst(),
      blue3 = stapelBlue.removeFirst(),
      yellow1 = stapelYellow.removeFirst(),
      yellow2 = stapelYellow.removeFirst(),
      yellow3 = stapelYellow.removeFirst(),
      yellow4 = stapelYellow.removeFirst(),
  ),
) {
  override fun toString() = spielfeld.toString()
}

fun calculate(
  spielfeld: Spielfeld,
  wuerfel1: Int,
  wuerfel2: Int,
  wuerfel3: Int,
) {
  val operators = listOf(
      OperatorPlus,
      OperatorMinus,
      OperatorDividor,
      OperatorMultiplier,
  )

  val combinations2 = listOf(
      wuerfel1 to wuerfel2,
      wuerfel2 to wuerfel3,
      wuerfel1 to wuerfel3,
  )

  val calculations2 = combinations2
      .flatMap { (first, second) -> operators.map { operator -> Triple(first.toDouble(), operator, second.toDouble()) } }
      .asSequence()
      .mapNotNull { (first, operator, second) ->
        val result = operator.calculcte(first, second)

        when {
          ceil(result) == floor(result) -> CalculationResult("$first $operator $second", result)
          else -> null
        }
      }
      .filter { it.number >= 1 }
      .distinct()
      .sorted()
      .toList()

  val combinations3 = listOf(
      Triple(wuerfel1, wuerfel2, wuerfel3),
      Triple(wuerfel1, wuerfel3, wuerfel2),
      Triple(wuerfel2, wuerfel1, wuerfel3),
      Triple(wuerfel2, wuerfel3, wuerfel1),
      Triple(wuerfel3, wuerfel1, wuerfel2),
      Triple(wuerfel3, wuerfel2, wuerfel1),
  )

  val calculations3 = combinations3
      .flatMap { (first, second, third) ->
        operators.flatMap { firstOperator ->
          operators.map { secondOperator ->
            Fifth(first.toDouble(), firstOperator, second.toDouble(), secondOperator, third.toDouble())
          }
        }
      }
      .asSequence()
      .mapNotNull { (first, firstOperator, second, secondOperator, third) ->
        val result = when {
//          firstOperator == OperatorDividor -> secondOperator.calculcte((first / second), third)
//          firstOperator == OperatorMultiplier -> secondOperator.calculcte((first * second), third)
//          secondOperator == OperatorDividor -> firstOperator.calculcte(first, (second / third))
//          secondOperator == OperatorMultiplier -> firstOperator.calculcte(first, (second * third))
          else -> secondOperator.calculcte(firstOperator.calculcte(first, second), third)
        }

        when {
          ceil(result) == floor(result) -> CalculationResult("$first $firstOperator $second $secondOperator $third", result)
          else -> null
        }
      }
      .filter { it.number >= 1 }
      .distinct()
      .sorted()
      .toList()

//  println()
//  println("Mit 2 Würfeln folgende Ergebnisse: $calculations2")
//  println("Mit 3 Würfeln folgende Ergebnisse: $calculations3")
  println()
  println("Wegnehmbar mit 2 Würfeln:\n${spielfeld.cards().mapNotNull { card -> calculations2.firstOrNull { it.number.toInt() == card.number } }.joinToString(separator = "\n") { "${it.number.toInt()} = ${it.equation}" }}")
  println("Wegnehmbar mit 3 Würfeln:\n${spielfeld.cards().mapNotNull { card -> calculations3.firstOrNull { it.number.toInt() == card.number } }.joinToString(separator = "\n") { "${it.number.toInt()} = ${it.equation}" }}")
  println()
}

data class CalculationResult(val equation: String, val number: Double) : Comparable<CalculationResult> {
  override fun compareTo(other: CalculationResult): Int {
    return number.compareTo(other.number)
  }
}

data class Spielfeld(
  val black: Card?,
  val red1: Card?,
  val red2: Card?,
  val blue1: Card?,
  val blue2: Card?,
  val blue3: Card?,
  val yellow1: Card?,
  val yellow2: Card?,
  val yellow3: Card?,
  val yellow4: Card?,
) {
  fun cards() = listOfNotNull(black, red1, red2, blue1, blue2, blue3, yellow1, yellow2, yellow3, yellow4)

  override fun toString(): String {
    return table {
      style {
        borderStyle = Solid
      }
      cellStyle {
        alignment = MiddleRight
        paddingLeft = 1
        paddingRight = 1
        borderLeft = true
        borderRight = true
        borderTop = true
        borderBottom = true
      }
      body {
        row {
          cell(black?.number) {
            alignment = MiddleCenter
            columnSpan = 12
          }
        }
        row {
          cell(red1?.number) {
            alignment = MiddleCenter
            columnSpan = 6
          }
          cell(red2?.number) {
            alignment = MiddleCenter
            columnSpan = 6
          }
        }
        row {
          cell(blue1?.number) {
            alignment = MiddleCenter
            columnSpan = 4
          }
          cell(blue2?.number) {
            alignment = MiddleCenter
            columnSpan = 4
          }
          cell(blue3?.number) {
            alignment = MiddleCenter
            columnSpan = 4
          }
        }
        row {
          cell(yellow1?.number) {
            alignment = MiddleCenter
            columnSpan = 3
          }
          cell(yellow2?.number) {
            alignment = MiddleCenter
            columnSpan = 3
          }
          cell(yellow3?.number) {
            alignment = MiddleCenter
            columnSpan = 3
          }
          cell(yellow4?.number) {
            alignment = MiddleCenter
            columnSpan = 3
          }
        }
      }
    }.toString()
  }
}

data class Card(
  val number: Int,
  val points: Int = 0,
)

val CONFIGURATION_RED = listOf(
    Card(23, 5),
    Card(26, 5),
    Card(33, 5),
    Card(44, 6),
    Card(50, 5),
    Card(54, 5),
    Card(63, 6),
    Card(70, 5),
    Card(80, 5),
)

val CONFIGURATION_BLUE = listOf(
    Card(19, 3),
    Card(22, 3),
    Card(25, 4),
    Card(27, 3),
    Card(28, 3),
    Card(32, 3),
    Card(35, 3),
    Card(42, 3),
    Card(45, 4),
    Card(48, 3),
    Card(56, 3),
    Card(60, 3),
    Card(72, 3),
)

val CONFIGURATION_YELLOW = listOf(
    Card(3, 1),
    Card(4, 1),
    Card(5, 1),
    Card(6, 1),
    Card(7, 1),
    Card(8, 1),
    Card(9, 1),
    Card(10, 1),
    Card(11, 1),
    Card(12, 1),
    Card(13, 1),
    Card(14, 1),
    Card(15, 1),
    Card(16, 1),
    Card(17, 2),
    Card(18, 1),
    Card(20, 1),
    Card(21, 2),
    Card(24, 1),
    Card(30, 2),
    Card(36, 2),
    Card(40, 2),
)

val CONFIGURATION_BLACK = listOf(
    Card(49, 7),
    Card(64, 7),
    Card(84, 7),
    Card(90, 7),
)
