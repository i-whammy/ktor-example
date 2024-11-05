package com.example.driver

import com.example.gateway.UserDriver
import com.example.gateway.UserEntity

class InMemoryUserDriver: UserDriver {
    private val users = listOf(
        UserEntity("12345", "John Doe", "john.doe@example.com")
    )
    override fun fetchUserEntity(userId: String): UserEntity? {
        return users.find { userId == it.userId }
    }
}