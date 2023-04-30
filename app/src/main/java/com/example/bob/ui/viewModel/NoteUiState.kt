package com.example.bob.ui.viewModel

import com.example.bob.data.Note
import java.time.LocalDate

class NoteUiState(
    val id: Int = 0,
    val date: LocalDate = LocalDate.now(),
    val note: String = ""
)

fun NoteUiState.toNote(): Note = Note(
    id = id,
    date = date,
    note = note
)

fun Note.toNoteUiState(): NoteUiState = NoteUiState(
    id = id,
    date = date,
    note = note
)

fun NoteUiState.isValid() : Boolean {
    return note.isNotBlank()
}