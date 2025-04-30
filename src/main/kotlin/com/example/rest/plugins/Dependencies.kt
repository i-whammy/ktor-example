package com.example.rest.plugins

import com.example.driver.InMemoryUserDriver
import com.example.gateway.UserDriver
import com.example.gateway.UserGateway
import com.example.usecase.UserPort
import com.example.usecase.UserUseCase
import io.ktor.server.application.*
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureDependencies() {
    install(Koin) {
        modules(appModule)
    }
}

val appModule = module {
    singleOf(::InMemoryUserDriver) { bind<UserDriver>() }
    singleOf(::UserGateway) { bind<UserPort>() }
    singleOf(::UserUseCase)

//    single<UserDriver> { InMemoryUserDriver()}
//    single<UserPort> { UserGateway(get())}
//    single { UserUseCase(get()) }
}