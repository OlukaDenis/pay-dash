package com.dennytech.data.local.di

import android.content.Context
import androidx.room.Room
import com.dennytech.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val APP_DATABASE_DB = "payway7.db"
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        APP_DATABASE_DB
    )
        .allowMainThreadQueries()
        .build()

    @Singleton
    @Provides
    fun provideTranDao(database: AppDatabase) = database.transactionDao()

}