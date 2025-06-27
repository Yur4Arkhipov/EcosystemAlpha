package com.example.ecosystemalpha.interactor

import com.example.ecosystemalpha.core.util.UiState
import com.example.ecosystemalpha.data.local.BinQueryEntity
import com.example.ecosystemalpha.domain.repository.BinHistoryRepository
import com.example.ecosystemalpha.domain.usecase.GetBinHistoryUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetBinHistoryUseCaseImpl @Inject constructor(
    private val repository: BinHistoryRepository
): GetBinHistoryUseCase {

    override suspend fun invoke(): Flow<UiState<List<BinQueryEntity>>> =
        repository.getAllQueries()
            .map { UiState.Success(it) as UiState<List<BinQueryEntity>> }
            .catch { e ->
                emit(UiState.Error(e.localizedMessage ?: "Unknown error"))
            }
}