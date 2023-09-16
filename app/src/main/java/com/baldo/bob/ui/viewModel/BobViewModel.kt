package com.baldo.bob.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.baldo.bob.dataStore.AppSettings
import com.baldo.bob.dataStore.UserInformations
import com.baldo.bob.dataStore.UserInformationsRepository
import com.baldo.bob.ui.compose.weight.viewModel.WeightUiState
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

    val appUIState: StateFlow<AppUiState> =
        userInformationsRepository.appSettingsPreferencesFlow.map { data ->
            AppUiState(
                 themeSetting = data.themeSetting
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AppUiState()
        )

    fun updateUserInformations(userInformations: UserInformations) {
        viewModelScope.launch {
            userInformationsRepository.saveUserInformations(userInformations)
        }
    }

    fun updateAppSettings(appSettings: AppSettings){
        viewModelScope.launch {
            userInformationsRepository.saveAppSettings(appSettings)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as com.baldo.bob.BobApplication)
                BobViewModel(application.userInformationsRepository)
            }
        }
    }
}