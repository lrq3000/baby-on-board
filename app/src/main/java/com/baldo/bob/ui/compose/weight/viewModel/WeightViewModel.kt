package com.baldo.bob.ui.compose.weight.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baldo.bob.data.Weight
import com.baldo.bob.data.WeightRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class WeightViewModel(weightRepository: WeightRepository) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val weightUiState: StateFlow<DisplayWeightUiState> =
        weightRepository.getAllWeightStream().map { DisplayWeightUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DisplayWeightUiState()
            )
}

data class DisplayWeightUiState(val weightList: List<Weight> = listOf())