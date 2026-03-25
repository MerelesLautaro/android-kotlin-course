package com.example.bankingapp.data.repository

import com.example.bankingapp.data.remote.api.AuthApi
import com.example.bankingapp.data.remote.dto.LoginRequestDto
import com.example.bankingapp.data.remote.dto.RegisterRequestDto
import com.example.bankingapp.data.remote.util.ErrorParser
import com.example.bankingapp.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun login(
        identifier: String,
        password: String
    ): Result<String> {

        return try {

            val response = api.login(
                LoginRequestDto(identifier, password)
            )

            if (response.isSuccessful) {

                val token = response.body()?.token

                if (token != null) {
                    Result.success(token)
                } else {
                    Result.failure(Exception("Empty token"))
                }

            } else {

                val errorMessage = ErrorParser.parseError(response)

                Result.failure<String>(Exception(errorMessage))

            }

        } catch (e: Exception) {

            Result.failure<String>(e)

        }

    }

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): Result<Unit> {

        return try {

            val response = api.register(
                RegisterRequestDto(name, email, password)
            )

            if (response.isSuccessful) {

                Result.success(Unit)

            } else {

                val errorMessage = ErrorParser.parseError(response)

                Result.failure<Unit>(Exception(errorMessage))

            }

        } catch (e: Exception) {

            Result.failure<Unit>(e)

        }

    }

}