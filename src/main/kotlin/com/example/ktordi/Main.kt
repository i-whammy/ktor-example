package com.example.ktordi

import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain



fun main() {
    EngineMain.main(arrayOf())
}

class GetUserIdsUseCase(val samplePort: SamplePort) {
    fun execute(): List<String> {
        return samplePort.getUserIds()
    }
}

interface SamplePort {
    fun getUserIds(): List<String>
}

fun Application.load() {
    dependencies {}
}

