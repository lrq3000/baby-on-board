package com.example.bob.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bob.BobApplication
import com.example.bob.dataStore.UserInformationsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class BobViewModel(
    private val userInformationsRepository: UserInformationsRepository
) : ViewModel() {

    /*private val _uiState = MutableStateFlow(BobUiState())
    val uiState: StateFlow<BobUiState> = _uiState.asStateFlow()*/

    val uiState:StateFlow<BobUiState> =
        userInformationsRepository.getUserName.map { userName ->
            BobUiState(userName)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = BobUiState()
        )

/*    var userName by mutableStateOf("")
    private set*/

    fun updateUserName(name:String){
        /*userName = name*/
        viewModelScope.launch {
            userInformationsRepository.saveUserName(name)
        }
    }

    companion object {
        val Factory:ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BobApplication)
                BobViewModel(application.userInformationsRepository)
            }
        }
    }

}