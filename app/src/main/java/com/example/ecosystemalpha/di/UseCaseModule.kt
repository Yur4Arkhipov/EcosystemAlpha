package com.example.ecosystemalpha.di

import com.example.ecosystemalpha.domain.repository.BinRepository
import com.example.ecosystemalpha.domain.usecase.GetBinInfoUseCase
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
}