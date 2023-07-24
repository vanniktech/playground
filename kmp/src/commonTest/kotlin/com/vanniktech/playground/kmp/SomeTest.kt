package com.vanniktech.playground.kmp

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.serialization.serializedValue
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlin.test.Test
import kotlin.test.assertEquals

class SomeTest {
  @Test fun foo() {
    val preferences = Preferences(MapSettings())
    assertEquals(expected = emptyList(), actual = preferences.list)

    val list = listOf("Hello", "World")
    preferences.list = list
    assertEquals(expected = list, actual = preferences.list)
  }

  class Preferences(settings: Settings) {
    @OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
    var list by settings.serializedValue(
      serializer = ListSerializer(String.serializer()),
      key = "something",
      defaultValue = emptyList(),
    )
  }
}
