package com.example.bankingapp.data.remote.api

import com.example.bankingapp.data.remote.dto.TransactionDetailDto
import com.example.bankingapp.data.remote.util.ApiConfig
import retrofit2.Response
import retrofit2.http.GET

interface AccountApi {

    @GET("${ApiConfig.ACCOUNT}/transactions")
    suspend fun getTransactions(): Response<List<TransactionDetailDto>>
}