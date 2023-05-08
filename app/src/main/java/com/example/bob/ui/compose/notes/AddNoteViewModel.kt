package com.example.bob.ui.compose.notes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bob.BobApplication
import com.example.bob.data.NotesRepository

class AddNoteViewModel(
    private val notesRepository: NotesRepository
) : ViewModel() {

    var noteUiState by mutableStateOf(NoteUiState())
        private set

    fun updateUiState(newNoteUiState: NoteUiState) {
        noteUiState = newNoteUiState.copy()
    }

    suspend fun saveNote() {
        if (noteUiState.isValid()) {
            notesRepository.insertNote(noteUiState.toNote())
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BobApplication)
                AddNoteViewModel(application.appDataContainer.notesRepository)
            }
        }
    }
}
