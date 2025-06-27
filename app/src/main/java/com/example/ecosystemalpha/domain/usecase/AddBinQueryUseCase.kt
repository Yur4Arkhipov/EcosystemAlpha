package com.example.ecosystemalpha.domain.usecase

import com.example.ecosystemalpha.core.util.UiState
import com.example.ecosystemalpha.data.local.BinQueryEntity

interface AddBinQueryUseCase {
    suspend operator fun invoke(query: BinQueryEntity): UiState<Unit>
}