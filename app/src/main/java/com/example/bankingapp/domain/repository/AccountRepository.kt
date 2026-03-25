package com.example.bankingapp.domain.repository

import com.example.bankingapp.data.remote.dto.TransactionDetailDto

interface AccountRepository {

    suspend fun getTransactions(): Result<List<TransactionDetailDto>>

}