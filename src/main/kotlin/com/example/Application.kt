package com.example

import com.example.api.rest.plugins.configureDependencies
import com.example.api.rest.plugins.configureRouting
import com.example.api.rest.plugins.configureSSERouting
import com.example.api.rest.plugins.configureSerializer
import com.example.mock.WireMockRunner
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) {
    WireMockRunner.run()
    EngineMain.main(args)
}

fun Application.module() {
    configureDependencies()
    configureSerializer()
    configureRouting()
    configureSSERouting()
}
