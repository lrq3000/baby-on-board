package com.example.bob.ui.compose.notes

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bob.data.NotesRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class NoteEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val notesRepository: NotesRepository
) : ViewModel() {

    var noteUiState by mutableStateOf(NoteUiState())
        private set

    private val noteId: Int = checkNotNull(savedStateHandle["noteId"])

    init {
        Log.e("noteId", "$noteId")
        viewModelScope.launch {
            noteUiState = notesRepository.getNoteStream(noteId)
                .filterNotNull()
                .first()
                .toNoteUiState()
        }
    }

    suspend fun updateNote() {


        if (noteUiState.isValid()) {
            notesRepository.updateNote(noteUiState.toNote())
        }
    }

    fun updateUiState(newNoteUiState: NoteUiState) {
        noteUiState = newNoteUiState.copy()
    }
}