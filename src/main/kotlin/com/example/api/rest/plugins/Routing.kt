package com.example.api.rest.plugins

import com.example.api.domain.User
import com.example.api.domain.UserId
import com.example.api.usecase.UserUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val userUseCase by inject<UserUseCase>()

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/users/{userId}") {
            val userId = call.pathParameters["userId"] ?: TODO()
            call.response.status(HttpStatusCode.OK)
            call.respond(userUseCase.fetchUser(UserId(userId)).toUserResponse())
        }
    }
}

data class UserResponse(val userId: String, val name: String, val emailAddress: String)

fun User.toUserResponse() = this.let { UserResponse(it.userId.idValue, it.name, it.emailAddress) }