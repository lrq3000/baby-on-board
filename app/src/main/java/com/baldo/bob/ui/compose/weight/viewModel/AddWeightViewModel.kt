package com.baldo.bob.ui.compose.weight.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.baldo.bob.BobApplication
import com.baldo.bob.data.WeightRepository
import com.baldo.bob.ui.compose.notes.AddNoteViewModel

class AddWeightViewModel(
    private val weightRepository: WeightRepository
) : ViewModel() {

    var weightUiState by mutableStateOf(WeightUiState())
        private set

    fun updateUiState(newWeightUiState: WeightUiState){
        weightUiState = newWeightUiState.copy()
    }

    suspend fun saveWeight(){
        if (weightUiState.isValid()){
            weightRepository.insertWeight(weightUiState.toWeight())
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BobApplication)
                AddWeightViewModel(application.appDataContainer.weightRepository)
            }
        }
    }
}