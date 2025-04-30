package com.example

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.koin.dsl.module

// インターフェース
interface Service {
    fun process(): String
}

// 実装A
class ServiceA : Service {
    override fun process(): String = "Processing with Service A"
}

// 実装B
class ServiceB : Service {
    override fun process(): String = "Processing with Service B"
}

// Koinモジュール
val appModule = module {
    single<Service>(named("typeA")) { ServiceA() }
    single<Service>(named("typeB")) { ServiceB() }
}

// KtorアプリケーションでKoinを使用するためのコンポーネント
class MyComponent(private val call: ApplicationCall) : KoinComponent {
    fun processRequest(): String {
        val type = call.parameters["type"] ?: TODO()
        val service: Service by inject(named(type))
        return service.process()
    }
}

fun Application.module() {
    install(org.koin.ktor.plugin.Koin) {
        modules(appModule)
    }

    routing {
        get("/process/{type}") {
            val component = MyComponent(call)
            call.respondText(component.processRequest())
        }
    }
}

fun main() {
    io.ktor.server.netty.EngineMain.main(arrayOf("-port=8080"))
}