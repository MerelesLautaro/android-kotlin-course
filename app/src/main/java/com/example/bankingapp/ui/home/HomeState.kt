package com.example.bankingapp.ui.home

import java.math.BigDecimal
import com.example.bankingapp.data.remote.dto.TransactionDetailDto

data class HomeState(

    val name: String = "",
    val balance: BigDecimal = BigDecimal.ZERO,

    val transactions: List<TransactionDetailDto> = emptyList(),

    val showBalance: Boolean = true,
    val selectedTab: Int = 0,

    val isLoading: Boolean = false,
    val error: String? = null

)