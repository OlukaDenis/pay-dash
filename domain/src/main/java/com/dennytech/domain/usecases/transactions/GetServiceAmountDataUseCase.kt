package com.dennytech.domain.usecases.transactions

import com.dennytech.domain.base.BaseFlowUseCase
import com.dennytech.domain.dispacher.AppDispatcher
import com.dennytech.domain.models.ServiceAmountDomainModel
import com.dennytech.domain.repository.ChartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetServiceAmountDataUseCase @Inject constructor(
    dispatcher: AppDispatcher,
    private val repository: ChartRepository
): BaseFlowUseCase<String, List<ServiceAmountDomainModel>>(dispatcher){
    override fun run(param: String?): Flow<List<ServiceAmountDomainModel>> {
        return repository.getServiceAmountGroup()
    }

}