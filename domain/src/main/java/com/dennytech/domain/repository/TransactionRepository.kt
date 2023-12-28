package com.dennytech.domain.repository

import com.dennytech.domain.models.TransactionDomainModel
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun saveTransaction(model: TransactionDomainModel)
    suspend fun getTransactionCount(): Int
    suspend fun fetchRemoteTransactions(): List<TransactionDomainModel>
    fun getTransactions(): Flow<List<TransactionDomainModel>>
}