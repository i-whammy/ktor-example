package com.example.api.gateway

import com.example.api.domain.User
import com.example.api.domain.UserId
import com.example.api.usecase.UserPort

class UserGateway(private val userDriver: UserDriver): UserPort {
    override fun fetchUser(userId: UserId): User? {
        return userDriver.fetchUserEntity(userId.idValue)?.let {
            User(it.userId.let(::UserId), it.name, it.emailAddress)
        }
    }
}

interface UserDriver {
    fun fetchUserEntity(userId: String): UserEntity?
}

data class UserEntity(val userId: String, val name: String, val emailAddress: String)