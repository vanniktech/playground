package com.vanniktech.playground.kotlin

import com.github.doyaaaaaken.kotlincsv.dsl.context.ExcessFieldsRowBehaviour
import com.github.doyaaaaaken.kotlincsv.dsl.context.InsufficientFieldsRowBehaviour
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import java.io.File

fun main() {
  val downloads = File(System.getenv("HOME")).resolve("Downloads")

  val files = downloads.listFiles()
    .orEmpty()
    .filter { it.extension == "csv" && (it.name.startsWith("flashcards-deck-") || it.name.startsWith("chinese")) }

  files.forEach { file ->
    val reader = csvReader {
      skipEmptyLine = true
      insufficientFieldsRowBehaviour = InsufficientFieldsRowBehaviour.EMPTY_STRING
      excessFieldsRowBehaviour = ExcessFieldsRowBehaviour.TRIM
    }

    val flashcards = reader.readAll(file).mapNotNull { row ->
      val front = row.getOrNull(0).takeIfNotBlank()
      val back = row.getOrNull(1).takeIfNotBlank()

      if (front != null && back != null) {
        Flashcard(
          front = front,
          back = back,
          explanation = row.getOrNull(2).takeIfNotBlank(),
        )
      } else {
        null
      }
    }

    check(flashcards)

    csvWriter().writeAll(
      rows = flashcards.map { card ->
        val lines = card.back.split("\n")
        listOf(
          lines.drop(1).joinToString(separator = "\n"),
          lines.first(),
          card.front,
        )
      },
      targetFile = file.parentFile!!.resolve("uta-${file.name}")
    )
  }
}

fun String?.takeIfNotBlank() = takeIf { it?.isNotBlank() == true }

data class Flashcard(
  val front: String,
  val back: String,
  val explanation: String?,
)

private fun check(flashcards: List<Flashcard>) {
  val allFronts = flashcards.flatMap { it.front.toCharArray().toList() }.map { it.toString() }.toSet()

  val errors = flashcards.flatMap { flashcard ->
    if (flashcard.explanation != null) {
      flashcard.explanation.toCharArray()
        .asSequence()
        .filterNot { it.isWhitespace() }
        .filterNot { it in setOf('N', 'i', 'k') }
        .filterNot { it in setOf('。', '？', '/', '，') }
        .filterNot { it in setOf('北', '京', '上', '海') }
        .mapNotNull { char ->
          if (!allFronts.contains(char.toString())) {
            MissingFront(
              char = char.toString(),
              explanation = flashcard.explanation
            )
          } else {
            null
          }
        }
        .toList()
    } else {
      emptyList()
    }
  }

  errors
    .distinctBy { it.char }
    .forEach {
      println("Can't find flashcard with front for \"${it.char}\" from ${it.explanation}")
    }
}

data class MissingFront(
  val char: String,
  val explanation: String,
)
