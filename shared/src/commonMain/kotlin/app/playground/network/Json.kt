package app.playground.network

import kotlinx.serialization.json.Json

val JSON = Json {
  // To coerce enum values.
  coerceInputValues = true

  encodeDefaults = false
  ignoreUnknownKeys = true
}
