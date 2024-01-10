package com.dennytech.domain.models

data class CategoryAmountDomainModel(
    val category: String,
    val totalAmount: Int,
    val period: String
)