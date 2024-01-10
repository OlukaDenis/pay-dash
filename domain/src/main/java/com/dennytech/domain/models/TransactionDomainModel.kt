package com.dennytech.domain.models

data class TransactionDomainModel(
    val id: Long,
    val amount: Int,
    val category: String,
    val service: String,
    val txFinish: String,
    val date: Long,
    val type: String
)