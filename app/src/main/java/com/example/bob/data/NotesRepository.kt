package com.example.bob.data

import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllNotesStream(): Flow<List<Note>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getNoteStream(id: Int): Flow<Note?>

    /**
     * Insert item in the data source
     */
    suspend fun insertNote(note: Note)

    /**
     * Delete item from the data source
     */
    suspend fun deleteNote(note: Note)

    /**
     * Update item in the data source
     */
    suspend fun updateNote(note: Note)
}