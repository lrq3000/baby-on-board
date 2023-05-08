package com.example.bob.ui.compose.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bob.data.Note
import com.example.bob.data.NotesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class NoteViewModel(notesRepository: NotesRepository) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val noteUiState: StateFlow<DisplayNoteUiState> =
        notesRepository.getAllNotesStream().map { DisplayNoteUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DisplayNoteUiState()
            )
}

data class DisplayNoteUiState(val noteList: List<Note> = listOf())