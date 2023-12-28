package com.dennytech.domain.usecases.transactions

import com.dennytech.domain.base.BaseSuspendUseCase
import com.dennytech.domain.dispacher.AppDispatcher
import com.dennytech.domain.repository.TransactionRepository
import javax.inject.Inject

class GetTransactionCountUseCase @Inject constructor(
    dispatcher: AppDispatcher,
    private val repository: TransactionRepository
): BaseSuspendUseCase<String, Int>(dispatcher){
    override suspend fun run(param: String?): Int {
        return repository.getTransactionCount()
    }
}