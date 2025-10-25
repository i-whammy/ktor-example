package com.example.api.rest.plugins.mock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.time.ZoneOffset

object WireMockRunner {
    private val logger = LoggerFactory.getLogger(this::class.java)
    private val wireMockServer = WireMockServer(8081)
    fun run() {
        wireMockServer.start()
        wireMockServer.stubFor(
            get(urlEqualTo("/sse"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/event-stream")
                        .withHeader("Cache-Control", "no-cache")
                        .withHeader("Connection", "keep-alive")
                        .withBody(createEvents(100))
                )
        )
        logger.info("WireMock server started.")
    }
}

private fun createEvents(time: Int): String {
    val builder = StringBuilder()
    repeat(time) { n ->
        builder.append("data: ${Event("Event message $n")}")
        builder.append("\n")
    }
    return builder.toString()
}

data class Event(
    val content: String, val created: Long = LocalDateTime.now().toInstant(
        ZoneOffset.UTC
    ).toEpochMilli()
)