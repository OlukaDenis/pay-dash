package com.dennytech.data.remote.models

data class TransactionRemoteModel(
    val Amount: Int,
    val Category: String,
    val Service: String,
    val TxFinish: String,
    val Type: String
)