package com.example.bankingapp.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankingapp.data.local.TokenManager
import com.example.bankingapp.domain.repository.AuthRepository
import kotlinx.coroutines.launch
import java.math.BigDecimal

class HomeViewModel(
    private val repository: AuthRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    var state by mutableStateOf(
        HomeState(
            name = "Lautaro",
            balance = BigDecimal("15432.75")
        )
    )
        private set

    fun toggleBalanceVisibility() {

        state = state.copy(
            showBalance = !state.showBalance
        )

    }

    fun selectTab(index: Int) {

        state = state.copy(
            selectedTab = index
        )

    }

    fun logout(onComplete: () -> Unit) {

        viewModelScope.launch {

            repository.logout()

            tokenManager.clearToken()

            onComplete()

        }

    }

}