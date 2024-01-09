package com.dennytech.domain.usecases.transactions

import com.dennytech.domain.base.BaseFlowUseCase
import com.dennytech.domain.dispacher.AppDispatcher
import com.dennytech.domain.models.CategoryAmountDomainModel
import com.dennytech.domain.repository.ChartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryAmountDataUseCase @Inject constructor(
    dispatcher: AppDispatcher,
    private val repository: ChartRepository
): BaseFlowUseCase<String, List<CategoryAmountDomainModel>>(dispatcher){
    override fun run(param: String?): Flow<List<CategoryAmountDomainModel>> {
        return repository.getCategoryAmountGroup()
    }

}