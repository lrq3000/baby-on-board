package com.example.bob.ui.compose.notes

import com.example.bob.data.Note
import java.util.Date

data class NoteUiState(
    val id: Int = 0,
    val date: Date = Date(),
    val note: String = "",
    val feeling: Int = 0
)

fun NoteUiState.toNote(): Note = Note(
    id = id,
    date = date,
    note = note,
    feeling = feeling

)

fun Note.toNoteUiState(): NoteUiState = NoteUiState(
    id = id,
    date = date,
    note = note,
    feeling = feeling
)

fun NoteUiState.isValid(): Boolean {
    return note.isNotBlank()
}