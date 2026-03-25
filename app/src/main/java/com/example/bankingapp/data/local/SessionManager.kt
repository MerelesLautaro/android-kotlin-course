package com.example.bankingapp.data.local

class SessionManager(
    private val tokenManager: TokenManager
) {

    suspend fun isLoggedIn(): Boolean {
        return tokenManager.getToken() != null
    }

    suspend fun logout() {
        tokenManager.clearToken()
    }
}