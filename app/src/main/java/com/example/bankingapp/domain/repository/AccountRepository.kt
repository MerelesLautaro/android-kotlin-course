package com.example.bankingapp.domain.repository

import com.example.bankingapp.data.remote.dto.TransactionDetailDto
import java.math.BigDecimal

interface AccountRepository {

    suspend fun getTransactions(): Result<List<TransactionDetailDto>>

    suspend fun deposit(amount: BigDecimal): Result<String>

    suspend fun withdraw(amount: BigDecimal): Result<String>

    suspend fun transfer(amount: BigDecimal, targetAccount: String): Result<String>
}