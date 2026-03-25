package com.example.bankingapp.data.repository

import com.example.bankingapp.data.remote.api.DashboardApi
import com.example.bankingapp.data.remote.dto.AccountDetailResponseDto
import com.example.bankingapp.data.remote.dto.UserDetailsResponseDto
import com.example.bankingapp.data.remote.util.ErrorParser
import com.example.bankingapp.domain.repository.DashboardRepository

class DashboardRepositoryImpl(
    private val api: DashboardApi
) : DashboardRepository {

    override suspend fun getUserInfo(): Result<UserDetailsResponseDto> {
        return try {
            val response = api.getUserInfo()
            if (response.isSuccessful) {
                response.body()?.let { Result.success(it) }
                    ?: Result.failure(Exception("Empty user info"))
            } else {
                Result.failure(Exception(ErrorParser.parseError(response)))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAccountDetail(): Result<AccountDetailResponseDto> {
        return try {
            val response = api.getAccountDetail()
            if (response.isSuccessful) {
                response.body()?.let { Result.success(it) }
                    ?: Result.failure(Exception("Empty account detail"))
            } else {
                Result.failure(Exception(ErrorParser.parseError(response)))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAccountByIndex(index: String): Result<AccountDetailResponseDto> {
        return try {
            val response = api.getAccountByIndex(index)
            if (response.isSuccessful) {
                response.body()?.let { Result.success(it) }
                    ?: Result.failure(Exception("Empty account detail for index $index"))
            } else {
                Result.failure(Exception(ErrorParser.parseError(response)))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}