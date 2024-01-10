package com.dennytech.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.dennytech.data.base.BaseDao
import com.dennytech.data.local.models.TransactionEntity
import com.dennytech.domain.models.CategoryAmountDomainModel
import com.dennytech.domain.models.ServiceAmountDomainModel
import com.dennytech.domain.models.TypeAmountDomainModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao : BaseDao<TransactionEntity> {

    @Query("SELECT * FROM  `transaction` ORDER BY txFinish")
    fun get(): Flow<List<TransactionEntity>>

    @Transaction
    @Query(
        "SELECT service, txFinish AS period, SUM(amount) AS totalAmount " +
                "FROM  `transaction` " +
                "GROUP BY service"
    )
    fun getServiceAmountGroup(): Flow<List<ServiceAmountDomainModel>>

    @Transaction
    @Query(
        "SELECT category, txFinish AS period, SUM(amount) AS totalAmount " +
                "FROM  `transaction` " +
                "GROUP BY category"
    )
    fun getCategoryAmountGroup(): Flow<List<CategoryAmountDomainModel>>

    @Transaction
    @Query(
        "SELECT type, txFinish AS period, SUM(amount) AS totalAmount " +
                "FROM  `transaction` " +
                "GROUP BY type"
    )
    fun getTypeAmountGroup(): Flow<List<TypeAmountDomainModel>>

    @Query("SELECT COUNT(*) FROM `transaction`")
    fun getCount(): Int

    @Query("SELECT * FROM  `transaction` WHERE id = :id")
    fun getById(id: Long): Flow<TransactionEntity?>

    @Query("DELETE FROM  `transaction`")
    fun clear()
}