package com.dennytech.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dennytech.data.local.dao.TransactionDao
import com.dennytech.data.local.models.TransactionEntity

@Database(
    entities = [
        TransactionEntity::class
    ],
    version = 1,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}