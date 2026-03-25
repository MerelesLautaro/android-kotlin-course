package com.example.bankingapp.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankingapp.data.local.TokenManager
import com.example.bankingapp.domain.repository.AccountRepository
import com.example.bankingapp.domain.repository.AuthRepository
import com.example.bankingapp.domain.repository.DashboardRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.math.BigDecimal

class HomeViewModel(
    private val authRepository: AuthRepository,
    private val dashboardRepository: DashboardRepository,
    private val accountRepository: AccountRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        loadDashboard()
    }

    private fun loadDashboard() {

        viewModelScope.launch {

            state = state.copy(isLoading = true)

            try {

                coroutineScope {

                    val userDeferred = async { dashboardRepository.getUserInfo() }
                    val accountDeferred = async { dashboardRepository.getAccountDetail() }
                    val transactionsDeferred = async { accountRepository.getTransactions() }

                    val user = userDeferred.await().getOrThrow()
                    val account = accountDeferred.await().getOrThrow()
                    val transactions = transactionsDeferred.await().getOrThrow()

                    state = state.copy(
                        name = user.name,
                        balance = account.balance,
                        transactions = transactions,
                        isLoading = false
                    )

                }

            } catch (e: Exception) {

                state = state.copy(
                    error = e.message,
                    isLoading = false
                )

            }

        }

    }
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

            authRepository.logout()

            tokenManager.clearToken()

            onComplete()

        }

    }

    fun deposit(amount: BigDecimal) {

        viewModelScope.launch {

            accountRepository.deposit(amount)

            loadDashboard()

        }

    }

    fun withdraw(amount: BigDecimal) {

        viewModelScope.launch {

            accountRepository.withdraw(amount)

            loadDashboard()

        }

    }

    fun transfer(amount: BigDecimal, targetAccount: String) {

        viewModelScope.launch {

            accountRepository.transfer(amount, targetAccount)

            loadDashboard()

        }

    }

}