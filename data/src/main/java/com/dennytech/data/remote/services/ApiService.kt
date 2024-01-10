package com.dennytech.data.remote.services

import com.dennytech.data.remote.models.TransactionRemoteModel
import retrofit2.http.GET

interface ApiService {

    @GET("api/transactions")
    suspend fun getMockTransactions(): List<TransactionRemoteModel>
}