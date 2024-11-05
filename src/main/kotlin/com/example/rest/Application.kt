package com.example.rest

import com.example.rest.plugins.configureDependencies
import com.example.rest.plugins.configureSerializer
import com.example.rest.plugins.configureRouting
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureDependencies()
    configureSerializer()
    configureRouting()
}
