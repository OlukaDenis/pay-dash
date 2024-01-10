package com.dennytech.domain.repository

import com.dennytech.domain.models.CategoryAmountDomainModel
import com.dennytech.domain.models.ServiceAmountDomainModel
import com.dennytech.domain.models.TypeAmountDomainModel
import kotlinx.coroutines.flow.Flow


interface ChartRepository {
    fun getServiceAmountGroup(): Flow<List<ServiceAmountDomainModel>>
    fun getTypeAmountGroup(): Flow<List<TypeAmountDomainModel>>
    fun getCategoryAmountGroup(): Flow<List<CategoryAmountDomainModel>>
}