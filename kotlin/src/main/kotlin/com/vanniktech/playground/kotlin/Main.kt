package com.vanniktech.playground.kotlin

import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.install
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.authentication
import io.ktor.server.auth.bearer
import io.ktor.server.auth.principal
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.RoutingContext
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

const val AUTH_BEARER = "bearer"

fun main() {
  embeddedServer(
    factory = Netty,
    port = 8080,
    host = "0.0.0.0",
    module = {
      install(StatusPages) {
        exception<Throwable> { call, cause ->
          call.respond(cause.message ?: "no exception message")
        }

        unhandled {
          it.respond("Unhandled")
        }
      }

      authentication {
        bearer(AUTH_BEARER) {
          realm = AUTH_BEARER

          authenticate {
            if (it.token == "foo") {
              "Valid token: ${it.token}"
            } else {
              null
            }
          }
        }
      }

      routing {
        authenticate(AUTH_BEARER) {
          route(Routes.Account.DELETE) {
            val principal = call.principal<String>() ?: throw IllegalStateException("no principal")
            call.respond(HttpStatusCode.OK, principal)
          }
        }
      }
    },
  )
    .start(wait = true)
}

private fun Routing.route(route: Route, call: suspend RoutingContext.() -> Unit) = when (val method = route.method) {
  HttpMethod.Delete -> delete(path = route.path, body = call)
  HttpMethod.Get -> get(path = route.path, body = call)
  HttpMethod.Post -> post(path = route.path, body = call)
  else -> error("Unsupported method $method")
}

data class Route internal constructor(
  val path: String,
  val method: HttpMethod,
)

object Routes {
  object Account {
    private const val ROOT = "account"

    val DELETE = Route(path = ROOT, HttpMethod.Delete)
  }
}
