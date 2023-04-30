package com.example.bob.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bob.data.NotesRepository

class NoteViewModel(private val notesRepository: NotesRepository) : ViewModel() {
    var noteUiState by mutableStateOf(NoteUiState())
        private set

    suspend fun saveNote(){
        if(noteUiState.isValid()){
            notesRepository.insertNote(noteUiState.toNote())
        }
    }
}