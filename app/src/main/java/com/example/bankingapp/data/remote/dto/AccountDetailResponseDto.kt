package com.example.bankingapp.data.remote.dto

import java.math.BigDecimal

data class AccountDetailResponseDto(
    val accountNumber: String,
    val balance: BigDecimal,
    val accountType: AccountTypeDto
)