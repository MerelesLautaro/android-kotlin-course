package com.example.bankingapp.data.repository

import com.example.bankingapp.data.remote.api.AccountApi
import com.example.bankingapp.data.remote.dto.TransactionDetailDto
import com.example.bankingapp.data.remote.util.ErrorParser
import com.example.bankingapp.domain.repository.AccountRepository

class AccountRepositoryImpl(
    private val api: AccountApi
) : AccountRepository {

    override suspend fun getTransactions(): Result<List<TransactionDetailDto>> {
        return try {
            val response = api.getTransactions()
            if (response.isSuccessful) {
                response.body()?.let { Result.success(it) }
                    ?: Result.failure(Exception("No transactions found"))
            } else {
                Result.failure(Exception(ErrorParser.parseError(response)))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}