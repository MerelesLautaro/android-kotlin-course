package com.example.bankingapp.data.remote.api

import com.example.bankingapp.data.remote.dto.GenericResponseDto
import com.example.bankingapp.data.remote.dto.TransactionDetailDto
import com.example.bankingapp.data.remote.dto.TransactionRequestDto
import com.example.bankingapp.data.remote.dto.TransferRequestDto
import com.example.bankingapp.data.remote.util.ApiConfig
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AccountApi {

    @GET("${ApiConfig.ACCOUNT}/transactions")
    suspend fun getTransactions(): Response<List<TransactionDetailDto>>

    @POST("${ApiConfig.ACCOUNT}/deposit")
    suspend fun deposit(
        @Body request: TransactionRequestDto
    ): Response<GenericResponseDto>

    @POST("${ApiConfig.ACCOUNT}/withdraw")
    suspend fun withdraw(
        @Body request: TransactionRequestDto
    ): Response<GenericResponseDto>

    @POST("${ApiConfig.ACCOUNT}/fund-transfer")
    suspend fun transfer(
        @Body request: TransferRequestDto
    ): Response<GenericResponseDto>
}