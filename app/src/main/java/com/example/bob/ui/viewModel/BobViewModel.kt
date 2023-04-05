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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class BobViewModel(
    private val userInformationsRepository: UserInformationsRepository
) : ViewModel() {

    /*private val _uiState = MutableStateFlow(BobUiState())
    val uiState: StateFlow<BobUiState> = _uiState.asStateFlow()*/

    val uiState: StateFlow<BobUiState> =
        userInformationsRepository.getUserInformations.map { userName ->
            BobUiState(userName)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = BobUiState()
        )

/*    var userName by mutableStateOf("")
    private set*/

    fun updateUserInformations(userInformations: UserInformations) {
        /*userName = name*/
        viewModelScope.launch {
            userInformationsRepository.saveUserInformations(userInformations)
        }
    }

/*    fun updateLastPeriodDate(date: LocalDate) {
        viewModelScope.launch {
            userInformationsRepository.saveLastPeriodDate(date)
        }
    }*/

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BobApplication)
                BobViewModel(application.userInformationsRepository)
            }
        }
    }

}