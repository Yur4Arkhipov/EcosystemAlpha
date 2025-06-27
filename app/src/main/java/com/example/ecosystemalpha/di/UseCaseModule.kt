package com.example.ecosystemalpha.di

import com.example.ecosystemalpha.domain.repository.BinHistoryRepository
import com.example.ecosystemalpha.domain.repository.BinRepository
import com.example.ecosystemalpha.domain.usecase.AddBinQueryUseCase
import com.example.ecosystemalpha.domain.usecase.GetBinHistoryUseCase
import com.example.ecosystemalpha.domain.usecase.GetBinInfoUseCase
import com.example.ecosystemalpha.interactor.AddBinQueryUseCaseImpl
import com.example.ecosystemalpha.interactor.GetBinHistoryUseCaseImpl
import com.example.ecosystemalpha.interactor.GetBinInfoUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetBinInfoUseCase(
        repository: BinRepository
    ): GetBinInfoUseCase {
        return GetBinInfoUseCaseImpl(repository)
    }

    @Provides
    fun provideAddBinQueryUseCase(
        repository: BinHistoryRepository
    ): AddBinQueryUseCase {
        return AddBinQueryUseCaseImpl(repository)
    }

    @Provides
    fun provideGetBinHistoryUseCase(
        repository: BinHistoryRepository
    ): GetBinHistoryUseCase {
        return GetBinHistoryUseCaseImpl(repository)
    }
}