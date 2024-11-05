package com.example.usecase

import com.example.domain.User
import com.example.domain.UserId

class UserUseCase(private val userPort: UserPort) {
    fun fetchUser(userId: UserId): User {
        return userPort.fetchUser(userId) ?: TODO()
    }
}

interface UserPort {
    fun fetchUser(userId: UserId): User?
}