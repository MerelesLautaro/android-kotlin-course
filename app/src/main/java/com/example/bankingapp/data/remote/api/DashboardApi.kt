package com.example.bankingapp.data.remote.api

import com.example.bankingapp.data.remote.dto.AccountDetailResponseDto
import com.example.bankingapp.data.remote.dto.UserDetailsResponseDto
import com.example.bankingapp.data.remote.util.ApiConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DashboardApi {

    @GET("${ApiConfig.DASHBOARD}/user")
    suspend fun getUserInfo(): Response<UserDetailsResponseDto>

    @GET("${ApiConfig.DASHBOARD}/account")
    suspend fun getAccountDetail(): Response<AccountDetailResponseDto>

    @GET("${ApiConfig.DASHBOARD}/account/{index}")
    suspend fun getAccountByIndex(
        @Path("index") index: String
    ): Response<AccountDetailResponseDto>
}