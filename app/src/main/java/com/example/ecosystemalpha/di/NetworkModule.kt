package com.example.ecosystemalpha.di

import com.example.ecosystemalpha.data.remote.services.BinApiService
import com.example.ecosystemalpha.data.repository.BinRepositoryImpl
import com.example.ecosystemalpha.domain.repository.BinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideBinApiService(retrofit: Retrofit): BinApiService {
        return retrofit.create(BinApiService::class.java)
    }

    @Provides
    fun provideBinRepository(api: BinApiService): BinRepository {
        return BinRepositoryImpl(api)
    }
}