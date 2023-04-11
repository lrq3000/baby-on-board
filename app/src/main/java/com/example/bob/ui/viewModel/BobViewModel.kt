package com.example.bob.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bob.BobApplication
import com.example.bob.dataStore.UserInformations
import com.example.bob.dataStore.UserInformationsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class BobViewModel(
    private val userInformationsRepository: UserInformationsRepository,
) : ViewModel() {

    val uiState: StateFlow<BobUiState> =
        userInformationsRepository.userPreferencesFlow.map { data ->
            BobUiState(
                userName = data.userName,
                userLastPeriodsDate = data.lastPeriodDate,
                userLastOvulationDate = data.lastOvulationDate

            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = BobUiState()
        )

    fun updateUserInformations(userInformations: UserInformations) {
        viewModelScope.launch {
            userInformationsRepository.saveUserInformations(userInformations)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BobApplication)
                BobViewModel(application.userInformationsRepository)
            }
        }
    }

}