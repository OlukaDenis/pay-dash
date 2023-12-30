package com.dennytech.domain.models

data class ServiceAmountDomainModel(
    val service: String,
    val totalAmount: Int


) {
    override fun toString(): String {
        return "ServiceAmountDomainModel(service='$service', totalAmount=$totalAmount)"
    }
}