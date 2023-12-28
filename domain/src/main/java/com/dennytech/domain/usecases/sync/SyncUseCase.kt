package com.dennytech.domain.usecases.sync

import com.dennytech.domain.base.BaseSuspendUseCase
import com.dennytech.domain.dispacher.AppDispatcher
import javax.inject.Inject

class SyncUseCase @Inject constructor(
    dispatcher: AppDispatcher
) : BaseSuspendUseCase<Unit, String?>(dispatcher) {
    override suspend fun run(param: Unit?): String? {
        TODO("Not yet implemented")
    }


}