package com.dennnytech.paydash.mappers

import com.dennnytech.paydash.models.RevenueUiModel
import com.dennytech.domain.models.TypeAmountDomainModel
import javax.inject.Inject

class TypeAmountToRevenueMapper @Inject constructor() : BaseUiMapper<RevenueUiModel, TypeAmountDomainModel>{
    override fun toUI(entity: TypeAmountDomainModel): RevenueUiModel {
        return RevenueUiModel(
            label = entity.type,
            amount = entity.totalAmount.toFloat(),
            period = entity.period
        )
    }

}