package com.dennytech.domain.usecases.transactions

import com.dennytech.domain.base.BaseFlowUseCase
import com.dennytech.domain.dispacher.AppDispatcher
import com.dennytech.domain.models.TransactionDomainModel
import com.dennytech.domain.repository.ChartRepository
import com.dennytech.domain.utils.Helpers
import kotlinx.coroutines.flow.Flow
import java.security.InvalidParameterException
import javax.inject.Inject

class FilterTransactionsUseCase @Inject constructor(
    dispatcher: AppDispatcher,
    private val repository: ChartRepository
): BaseFlowUseCase<FilterTransactionsUseCase.Param, List<TransactionDomainModel>>(dispatcher){

    data class Param(
        val startDate: String,
        val endDate: String
    )

    override fun run(param: Param?): Flow<List<TransactionDomainModel>> {

        param?.let {
            val start = Helpers.dateStringToMillis(it.startDate)
            val end = Helpers.dateStringToMillis(it.endDate)
                return repository.filterTransactions(start, end)
        } ?: throw InvalidParameterException()

    }

}