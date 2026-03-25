package com.example.bankingapp.data.remote.dto

import java.math.BigDecimal

data class TransferRequestDto(
    val amount: BigDecimal,
    val targetAccountNumber: String
)