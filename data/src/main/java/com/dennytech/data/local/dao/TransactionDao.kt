package com.dennytech.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.dennytech.data.base.BaseDao
import com.dennytech.data.local.models.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao : BaseDao<TransactionEntity> {

    @Query("SELECT * FROM  `transaction` ORDER BY txFinish")
    fun get(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM  `transaction` WHERE id = :id")
    fun getById(id: Long): Flow<TransactionEntity?>

    @Query("DELETE FROM  `transaction`")
    fun clear()
}