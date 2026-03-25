package com.example.bankingapp.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankingapp.data.local.TokenManager
import com.example.bankingapp.domain.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val tokenManager: TokenManager,
    private val repository: AuthRepository

) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onIdentifierChange(value: String) {
        state = state.copy(identifier = value)
    }

    fun onPasswordChange(value: String) {
        state = state.copy(password = value)
    }

    fun onLoginClick() {

        if (state.identifier.isBlank() || state.password.isBlank()) {
            state = state.copy(error = "Todos los campos son obligatorios")
            return
        }

        state = state.copy(error = null)

        viewModelScope.launch {

            val result = repository.login(
                state.identifier,
                state.password
            )

            result.onSuccess { token ->

                viewModelScope.launch {

                    tokenManager.saveToken(token)

                }

            }
                .onFailure {

                    state = state.copy(
                        error = it.message ?: "Unknow error"
                    )

                }

        }

    }

}