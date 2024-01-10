package com.dennytech.data.impl

import com.dennytech.data.local.dao.TransactionDao
import com.dennytech.domain.models.CategoryAmountDomainModel
import com.dennytech.domain.models.ServiceAmountDomainModel
import com.dennytech.domain.models.TypeAmountDomainModel
import com.dennytech.domain.repository.ChartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChartRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
): ChartRepository {
    override fun getServiceAmountGroup(): Flow<List<ServiceAmountDomainModel>> {
        return transactionDao.getServiceAmountGroup()
    }

    override fun getTypeAmountGroup(): Flow<List<TypeAmountDomainModel>> {
        return transactionDao.getTypeAmountGroup()
    }

    override fun getCategoryAmountGroup(): Flow<List<CategoryAmountDomainModel>> {
        return transactionDao.getCategoryAmountGroup()
    }
}