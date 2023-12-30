package com.dennytech.domain.repository

import com.dennytech.domain.models.ServiceAmountDomainModel
import kotlinx.coroutines.flow.Flow


interface ChartRepository {
    fun getServiceAmountGroup(): Flow<List<ServiceAmountDomainModel>>
}