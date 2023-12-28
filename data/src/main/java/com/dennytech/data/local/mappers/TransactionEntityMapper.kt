package com.dennytech.data.local.mappers

import com.dennytech.data.local.models.TransactionEntity
import com.dennytech.domain.models.TransactionDomainModel
import javax.inject.Inject

class TransactionEntityMapper @Inject constructor(): BaseLocalMapper<TransactionEntity, TransactionDomainModel> {
    override fun toDomain(entity: TransactionEntity): TransactionDomainModel {
        return TransactionDomainModel(
            id = entity.id,
            amount = entity.amount,
            category = entity.category,
            service = entity.service,
            txFinish = entity.txFinish,
            type = entity.type
        )
    }

    override fun toLocal(entity: TransactionDomainModel): TransactionEntity {
        return TransactionEntity(
            id = entity.id,
            amount = entity.amount,
            category = entity.category,
            service = entity.service,
            txFinish = entity.txFinish,
            type = entity.type
        )
    }
}