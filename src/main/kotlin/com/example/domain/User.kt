package com.example.domain

data class UserId(val idValue: String)

data class User(val userId: UserId, val name: String, val emailAddress: String)