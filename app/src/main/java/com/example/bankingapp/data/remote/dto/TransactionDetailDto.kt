package com.example.bankingapp.data.remote.dto


import java.math.BigDecimal

data class TransactionDetailDto(
    val id: Long,
    val amount: BigDecimal,
    val transactionType: String,
    val transactionStatus: String,
    val transactionDate: Long,
    val sourceAccountNumber: String,
    val targetAccountNumber: String
)