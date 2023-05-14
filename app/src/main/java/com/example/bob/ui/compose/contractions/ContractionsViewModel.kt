package com.example.bob.ui.compose.contractions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bob.data.ContactionsRepository
import com.example.bob.data.Contraction
import com.example.bob.ui.compose.notes.NoteUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ContractionsViewModel(private val contactionsRepository: ContactionsRepository) :
    ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val displayContractionUiState: StateFlow<DisplayContractionUiState> =
        contactionsRepository.getAllContractionsStream().map { DisplayContractionUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DisplayContractionUiState()
            )

    var contractionUiState by mutableStateOf(ContractionUiState())
        private set

    suspend fun saveContraction() {
        contactionsRepository.insertContraction(contractionUiState.toContraction())
    }
}

data class DisplayContractionUiState(val contractionList: List<Contraction> = listOf())