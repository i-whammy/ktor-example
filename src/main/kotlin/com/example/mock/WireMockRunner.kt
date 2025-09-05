package com.example.mock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import org.slf4j.LoggerFactory

object WireMockRunner {
    private val logger = LoggerFactory.getLogger(this::class.java)
    private val wireMockServer = WireMockServer(8081)
    fun run() {
        wireMockServer.start()
        wireMockServer.stubFor(get(urlEqualTo("/sse"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "text/event-stream")
                .withHeader("Cache-Control", "no-cache")
                .withHeader("Connection", "keep-alive")
                .withBody(
                    """
                    data: Message 1

                    data: Message 2

                    data: Message 3

                    """
                )))
        logger.info("WireMock server started.")
    }
}