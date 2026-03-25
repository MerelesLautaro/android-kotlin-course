package com.example.bankingapp.ui.session

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankingapp.data.local.SessionManager
import kotlinx.coroutines.launch

class SessionViewModel(
    private val sessionManager: SessionManager
) : ViewModel() {

    var isLoggedIn by mutableStateOf<Boolean?>(null)
        private set

    init {
        checkSession()
    }

    private fun checkSession() {
        viewModelScope.launch {
            isLoggedIn = sessionManager.isLoggedIn()
        }
    }
}