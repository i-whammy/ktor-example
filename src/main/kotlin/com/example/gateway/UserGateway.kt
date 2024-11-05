package com.example.gateway

import com.example.domain.User
import com.example.domain.UserId
import com.example.usecase.UserPort

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