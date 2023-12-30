package com.dennytech.data.impl

import com.dennytech.data.local.dao.TransactionDao
import com.dennytech.domain.models.ServiceAmountDomainModel
import com.dennytech.domain.repository.ChartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChartRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
): ChartRepository {
    override fun getServiceAmountGroup(): Flow<List<ServiceAmountDomainModel>> {
        return transactionDao.getServiceAmountGroup()
    }
}