package com.example.ecosystemalpha.interactor

import com.example.ecosystemalpha.core.util.UiState
import com.example.ecosystemalpha.domain.model.BinInfo
import com.example.ecosystemalpha.domain.repository.BinRepository
import com.example.ecosystemalpha.domain.usecase.GetBinInfoUseCase
import jakarta.inject.Inject
import retrofit2.HttpException
import java.io.IOException

class GetBinInfoUseCaseImpl @Inject constructor(
    private val repository: BinRepository
): GetBinInfoUseCase {

    override suspend operator fun invoke(bin: String): UiState<BinInfo> {
        return try {
            val result = repository.getBinInfo(bin)
            UiState.Success(result)
        } catch (e: HttpException) {
            UiState.Error("Server error code: ${e.code()}")
        } catch (e: IOException) {
            UiState.Error("Network unavailable")
        } catch (e: Exception) {
            UiState.Error("Undefined error")
        }
    }
}