package com.example.bankingapp.domain.repository

interface AuthRepository {

    suspend fun login(
        identifier: String,
        password: String
    ): Result<String>

    suspend fun register(
        name: String,
        email: String,
        password: String
    ): Result<Unit>

    suspend fun logout(): Result<Unit>

}