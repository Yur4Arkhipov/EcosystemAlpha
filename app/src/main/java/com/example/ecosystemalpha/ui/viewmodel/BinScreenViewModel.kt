package com.example.ecosystemalpha.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecosystemalpha.core.util.UiState
import com.example.ecosystemalpha.data.local.BinQueryEntity
import com.example.ecosystemalpha.domain.model.BinInfo
import com.example.ecosystemalpha.domain.usecase.AddBinQueryUseCase
import com.example.ecosystemalpha.domain.usecase.GetBinInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BinScreenViewModel @Inject constructor(
    private val getBinInfoUseCase: GetBinInfoUseCase,
    private val addBinQueryUseCase: AddBinQueryUseCase
): ViewModel() {

    private val _binInfo = MutableStateFlow<UiState<BinInfo>>(UiState.Idle)
    val binInfo: StateFlow<UiState<BinInfo>> = _binInfo

    private val _addState = MutableStateFlow<UiState<Unit>>(UiState.Idle)

    fun loadBin(bin: String) {
        viewModelScope.launch {
            _binInfo.value = UiState.Loading
            val result: UiState<BinInfo> = getBinInfoUseCase(bin)
            _binInfo.value = result
        }
    }

    fun addQuery(query: BinQueryEntity) {
        viewModelScope.launch {
            _addState.value = UiState.Loading
            _addState.value = addBinQueryUseCase(query)
        }
    }

}