package com.example.ecosystemalpha.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecosystemalpha.core.util.UiState
import com.example.ecosystemalpha.data.local.BinQueryEntity
import com.example.ecosystemalpha.domain.usecase.GetBinHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryScreenViewModel @Inject constructor(
    private val getBinHistoryUseCase: GetBinHistoryUseCase
): ViewModel() {

    private val _historyState = MutableStateFlow<UiState<List<BinQueryEntity>>>(UiState.Loading)
    val historyState: StateFlow<UiState<List<BinQueryEntity>>> = _historyState

    init {
        loadHistory()
    }

    fun loadHistory() {
        viewModelScope.launch {
            _historyState.value = UiState.Loading
            getBinHistoryUseCase().collect { result ->
                _historyState.value = result
            }
        }
    }
}