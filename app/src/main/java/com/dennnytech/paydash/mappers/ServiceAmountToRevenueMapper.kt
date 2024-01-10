package com.dennnytech.paydash.mappers

import com.dennnytech.paydash.models.RevenueUiModel
import com.dennytech.domain.models.ServiceAmountDomainModel
import javax.inject.Inject

class ServiceAmountToRevenueMapper @Inject constructor(): BaseUiMapper<RevenueUiModel, ServiceAmountDomainModel> {
    override fun toUI(entity: ServiceAmountDomainModel): RevenueUiModel {
        return RevenueUiModel(
            label = entity.service,
            amount = entity.totalAmount.toFloat(),
            period = entity.period
        )
    }
}