package com.example.api.usecase

import com.example.api.domain.User
import com.example.api.domain.UserId

class UserUseCase(private val userPort: UserPort) {
    fun fetchUser(userId: UserId): User {
        return userPort.fetchUser(userId) ?: TODO()
    }
}

interface UserPort {
    fun fetchUser(userId: UserId): User?
}