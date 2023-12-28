package com.dennytech.domain.usecases.sync

import com.dennytech.domain.base.BaseFlowUseCase
import com.dennytech.domain.dispacher.AppDispatcher
import com.dennytech.domain.models.Resource
import com.dennytech.domain.repository.TransactionRepository
import com.dennytech.domain.repository.UtilRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SyncUseCase @Inject constructor(
    dispatcher: AppDispatcher,
    private val utilRepository: UtilRepository,
    private val transactionRepository: TransactionRepository
) : BaseFlowUseCase<Unit, Resource<String>>(dispatcher) {
    override fun run(param: Unit?): Flow<Resource<String>> = flow {
        emit(Resource.Loading)

        try {
            val list = runBlocking { transactionRepository.fetchRemoteTransactions() }

            list.map {
                runBlocking { transactionRepository.saveTransaction(it) }
            }

            emit(Resource.Success("Success"))
        } catch (t: Throwable) {
            emit(Resource.Error(utilRepository.getNetworkError(t)))
        }
    }

}