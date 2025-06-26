package com.example.ecosystemalpha.interactor

import com.example.ecosystemalpha.domain.model.BinInfo
import com.example.ecosystemalpha.domain.repository.BinRepository
import com.example.ecosystemalpha.domain.usecase.GetBinInfoUseCase
import jakarta.inject.Inject

class GetBinInfoUseCaseImpl @Inject constructor(
    private val repository: BinRepository
): GetBinInfoUseCase {

    override suspend fun invoke(bin: String): BinInfo {
        return repository.getBinInfo(bin)
    }
}