package com.example.api.rest.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.sse.*
import io.ktor.sse.*
import kotlin.time.Duration.Companion.milliseconds

fun Application.configureSSERouting() {
    install(SSE)
    routing {
        sse("/events") {
            heartbeat {
                period = 500.milliseconds
                event = ServerSentEvent("heart beating")
            }
        }
    }
}
