package com.example.ecosystemalpha.interactor

import android.database.sqlite.SQLiteException
import com.example.ecosystemalpha.core.util.UiState
import com.example.ecosystemalpha.data.local.BinQueryEntity
import com.example.ecosystemalpha.domain.repository.BinHistoryRepository
import com.example.ecosystemalpha.domain.usecase.AddBinQueryUseCase
import javax.inject.Inject

class AddBinQueryUseCaseImpl @Inject constructor(
    private val repository: BinHistoryRepository
) : AddBinQueryUseCase {

    override suspend fun invoke(query: BinQueryEntity): UiState<Unit> {
        return try {
            repository.addQuery(query)
            UiState.Success(Unit)
        } catch (e: SQLiteException) {
            UiState.Error("Database error: ${e.message}")
        } catch (e: Exception) {
            UiState.Error("Unknown error: ${e.message}")
        }
    }
}