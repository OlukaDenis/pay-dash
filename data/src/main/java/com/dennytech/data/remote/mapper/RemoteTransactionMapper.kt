package com.dennytech.data.remote.mapper

import android.annotation.SuppressLint
import com.dennytech.data.remote.models.TransactionRemoteModel
import com.dennytech.domain.models.TransactionDomainModel
import com.dennytech.domain.utils.Helpers
import javax.inject.Inject

class RemoteTransactionMapper @Inject constructor(): BaseRemoteMapper<TransactionRemoteModel, TransactionDomainModel> {

    override fun toDomain(entity: TransactionRemoteModel): TransactionDomainModel {
        return TransactionDomainModel(
            id = 0L,
            amount = entity.Amount,
            category = entity.Category,
            service = entity.Service,
            txFinish = entity.TxFinish,
            type = entity.Type,
            date = Helpers.dateStringToMillis(entity.TxFinish)
        )
    }

}