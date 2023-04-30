package com.example.bob.data

import kotlinx.coroutines.flow.Flow

class OfflineNotesRepository(private val notesDao: NotesDao) :
    NotesRepository {
    override fun getAllNotesStream(): Flow<List<Note>> =
        notesDao.getAllNote()

    override fun getNoteStream(id: Int): Flow<Note?> =
        notesDao.getNote(id)

    override suspend fun insertNote(note: Note) =
        notesDao.insert(note)

    override suspend fun deleteNote(note: Note) =
        notesDao.delete(note)

    override suspend fun updateNote(note: Note) =
        notesDao.update(note)
}