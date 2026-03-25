package com.example.bankingapp.data.repository

import com.example.bankingapp.data.remote.api.AccountApi
import com.example.bankingapp.data.remote.dto.TransactionDetailDto
import com.example.bankingapp.data.remote.dto.TransactionRequestDto
import com.example.bankingapp.data.remote.dto.TransferRequestDto
import com.example.bankingapp.data.remote.util.ErrorParser
import com.example.bankingapp.domain.repository.AccountRepository
import java.math.BigDecimal

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

    override suspend fun deposit(amount: BigDecimal): Result<String> {

        return try {

            val response = api.deposit(TransactionRequestDto(amount))

            if (response.isSuccessful) {
                Result.success(response.body()?.message ?: "Success")
            } else {
                Result.failure(Exception("Deposit failed"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    override suspend fun withdraw(amount: BigDecimal): Result<String> {

        return try {

            val response = api.withdraw(TransactionRequestDto(amount))

            if (response.isSuccessful) {
                Result.success(response.body()?.message ?: "Success")
            } else {
                Result.failure(Exception("Withdraw failed"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    override suspend fun transfer(amount: BigDecimal, targetAccount: String): Result<String> {

        return try {

            val response = api.transfer(
                TransferRequestDto(amount, targetAccount)
            )

            if (response.isSuccessful) {
                Result.success(response.body()?.message ?: "Success")
            } else {
                Result.failure(Exception("Transfer failed"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }

    }

}