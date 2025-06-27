package com.example.ecosystemalpha.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecosystemalpha.core.util.UiState
import com.example.ecosystemalpha.domain.model.BinInfo
import com.example.ecosystemalpha.domain.usecase.GetBinInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BinScreenViewModel @Inject constructor(
    private val getBinInfoUseCase: GetBinInfoUseCase
): ViewModel() {

    private val _binInfo = MutableStateFlow<UiState<BinInfo>>(UiState.Idle)
    val binInfo: StateFlow<UiState<BinInfo>> = _binInfo

    fun loadBin(bin: String) {
        viewModelScope.launch {
            val result: UiState<BinInfo> = getBinInfoUseCase(bin)
            _binInfo.value = UiState.Loading
            when(result) {
                is UiState.Success -> _binInfo.value = result
                is UiState.Error -> _binInfo.value = result
                else -> _binInfo.value = UiState.Error("Unknown state")
            }
        }
    }
}