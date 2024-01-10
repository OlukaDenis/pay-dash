package com.dennytech.data.di

import com.dennytech.data.impl.ChartRepositoryImpl
import com.dennytech.data.impl.TransactionRepositoryImpl
import com.dennytech.data.impl.UtilRepositoryImpl
import com.dennytech.domain.repository.ChartRepository
import com.dennytech.domain.repository.TransactionRepository
import com.dennytech.domain.repository.UtilRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindUtilRepository(impl: UtilRepositoryImpl): UtilRepository

    @Singleton
    @Binds
    fun bindTransactionRepository(impl: TransactionRepositoryImpl): TransactionRepository

    @Singleton
    @Binds
    fun bindChartRepository(impl: ChartRepositoryImpl): ChartRepository
}