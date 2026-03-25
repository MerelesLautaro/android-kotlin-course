package com.example.bankingapp.ui.home

import java.math.BigDecimal

data class HomeState(

    val name: String = "",
    val balance: BigDecimal? = null,

    val showBalance: Boolean = true,

    val selectedTab: Int = 0

)