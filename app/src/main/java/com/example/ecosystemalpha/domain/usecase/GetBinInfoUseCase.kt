package com.example.ecosystemalpha.domain.usecase

import com.example.ecosystemalpha.core.util.UiState
import com.example.ecosystemalpha.domain.model.BinInfo

interface GetBinInfoUseCase {
    suspend operator fun invoke(bin: String): UiState<BinInfo>
}