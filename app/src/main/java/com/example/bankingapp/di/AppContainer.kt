package com.example.bankingapp.di

import android.content.Context
import com.example.bankingapp.data.local.TokenManager
import com.example.bankingapp.data.remote.RetrofitClient
import com.example.bankingapp.data.remote.api.AccountApi
import com.example.bankingapp.data.remote.api.AuthApi
import com.example.bankingapp.data.remote.api.DashboardApi
import com.example.bankingapp.data.repository.AccountRepositoryImpl
import com.example.bankingapp.data.repository.AuthRepositoryImpl
import com.example.bankingapp.data.repository.DashboardRepositoryImpl

class AppContainer(context: Context) {

    val tokenManager = TokenManager(context)

    val retrofit = RetrofitClient.create(context)

    val authApi: AuthApi = retrofit.create(AuthApi::class.java)
    val dashboardApi: DashboardApi = retrofit.create(DashboardApi::class.java)
    val accountApi: AccountApi = retrofit.create(AccountApi::class.java)

    val authRepository = AuthRepositoryImpl(authApi)

    val dashboardRepository = DashboardRepositoryImpl(dashboardApi)

    val accountRepository = AccountRepositoryImpl(accountApi)
}