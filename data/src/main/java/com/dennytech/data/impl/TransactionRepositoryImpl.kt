package com.dennytech.data.impl

import com.dennytech.data.local.dao.TransactionDao
import com.dennytech.data.local.mappers.TransactionEntityMapper
import com.dennytech.data.remote.mapper.RemoteTransactionMapper
import com.dennytech.data.remote.services.ApiService
import com.dennytech.domain.models.TransactionDomainModel
import com.dennytech.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val service: ApiService,
    private val transactionDao: TransactionDao,
    private val transactionEntityMapper: TransactionEntityMapper,
    private val remoteTransactionMapper: RemoteTransactionMapper
) : TransactionRepository {
    override suspend fun saveTransaction(model: TransactionDomainModel) {
        transactionDao.insert(transactionEntityMapper.toLocal(model))
    }

    override suspend fun fetchRemoteTransactions(): List<TransactionDomainModel> {
        return try {
            val list = service.getMockTransactions()
            list.map { remoteTransactionMapper.toDomain(it) }
        } catch (t: Throwable) {
            throw t
        }
    }

    override fun getTransactions(): Flow<List<TransactionDomainModel>> {
        return transactionDao.get().map { list ->
            list.map { transactionEntityMapper.toDomain(it) }
        }
    }
}