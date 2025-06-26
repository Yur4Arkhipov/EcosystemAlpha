package com.example.ecosystemalpha.di

import com.example.ecosystemalpha.data.remote.services.BinApiService
import com.example.ecosystemalpha.data.repository.BinRepositoryImpl
import com.example.ecosystemalpha.domain.repository.BinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideBinRepository(api: BinApiService): BinRepository {
        return BinRepositoryImpl(api)
    }
}