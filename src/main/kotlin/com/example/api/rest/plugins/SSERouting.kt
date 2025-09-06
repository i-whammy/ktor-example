package com.example.api.rest.plugins

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.sse.*
import io.ktor.sse.ServerSentEvent
import io.ktor.utils.io.*
import kotlinx.coroutines.delay
import kotlinx.io.readLine

fun Application.configureSSERouting() {
    install(SSE)
    routing {
        sse("/events") {
            val client = HttpClient(CIO)
            client.prepareGet("http://localhost:8081/sse") {
                method = HttpMethod.Get
                headers {
                    append("Accept", "text/event-stream")
                }
            }.execute { response ->
                val channel: ByteReadChannel = response.body()
                while (!channel.exhausted()) {
                    var line: String?
                    val buffer = channel.readBuffer()
                    while (buffer.readLine().also { line = it } != null) {
                        delay(500)
                        send(ServerSentEvent(line))
                    }
                }
            }
        }
    }
}
