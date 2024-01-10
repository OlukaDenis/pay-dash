package com.dennytech.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val amount: Int,
    val category: String,
    val service: String,
    val txFinish: String,
    val date: Long,
    val type: String
)