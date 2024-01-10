package com.dennnytech.paydash.models

data class TransactionUiModel(
    val id: Long,
    val amount: Int,
    val category: String,
    val service: String,
    val date: Long,
    val type: String
)
