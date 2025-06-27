package com.example.ecosystemalpha.domain.usecase

import com.example.ecosystemalpha.core.util.UiState
import com.example.ecosystemalpha.data.local.BinQueryEntity
import kotlinx.coroutines.flow.Flow

interface GetBinHistoryUseCase {
    suspend operator fun invoke(): Flow<UiState<List<BinQueryEntity>>>
}