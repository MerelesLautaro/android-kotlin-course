package com.example.bankingapp.data.remote.dto

import java.math.BigDecimal

data class TransactionRequestDto(
    val amount: BigDecimal
)