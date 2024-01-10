package com.dennnytech.paydash.mappers

import com.dennnytech.paydash.models.RevenueUiModel
import com.dennytech.domain.models.CategoryAmountDomainModel
import javax.inject.Inject

class CategoryAmountToRevenueMapper @Inject constructor(): BaseUiMapper<RevenueUiModel, CategoryAmountDomainModel> {
    override fun toUI(entity: CategoryAmountDomainModel): RevenueUiModel {
        return RevenueUiModel(
            label = entity.category,
            amount = entity.totalAmount.toFloat(),
            period = entity.period
        )
    }
}