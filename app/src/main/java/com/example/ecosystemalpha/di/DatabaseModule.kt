package com.example.ecosystemalpha.di

import android.content.Context
import androidx.room.Room
import com.example.ecosystemalpha.data.local.BinQueriesDao
import com.example.ecosystemalpha.data.local.QueriesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): QueriesDatabase {
        return Room.databaseBuilder(
            context,
            QueriesDatabase::class.java,
            "QueriesDatabase"
        ).build()
    }

    @Provides
    fun provideBinQueryDao(database: QueriesDatabase): BinQueriesDao {
        return database.binQueriesDao()
    }
}