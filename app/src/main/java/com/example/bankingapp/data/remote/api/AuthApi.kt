package com.example.bankingapp.data.remote.api

import com.example.bankingapp.data.remote.dto.LoginRequestDto
import com.example.bankingapp.data.remote.dto.RegisterRequestDto
import com.example.bankingapp.data.remote.dto.TokenResponseDto
import com.example.bankingapp.data.remote.util.ApiConfig
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("${ApiConfig.USERS}/login")
    suspend fun login(
        @Body request: LoginRequestDto
    ): Response<TokenResponseDto>

    @POST("${ApiConfig.USERS}/register")
    suspend fun register(
        @Body request: RegisterRequestDto
    ): Response<Unit>


    @GET("${ApiConfig.USERS}/logout")
    suspend fun logout(): Response<Unit>

}