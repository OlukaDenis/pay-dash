package com.dennnytech.paydash.mappers

import android.annotation.SuppressLint
import com.dennnytech.paydash.models.TransactionUiModel
import com.dennytech.domain.models.TransactionDomainModel
import com.dennytech.domain.utils.Helpers
import javax.inject.Inject

class TransactionUiMapper @Inject constructor(): BaseUiMapper<TransactionUiModel, TransactionDomainModel> {

    override fun toUI(entity: TransactionDomainModel): TransactionUiModel {
        return TransactionUiModel(
            id = entity.id,
            category = entity.category,
            date = Helpers.dateStringToMillis(entity.txFinish),
            type = entity.type,
            amount = entity.amount,
            service = entity.service
        )
    }
}