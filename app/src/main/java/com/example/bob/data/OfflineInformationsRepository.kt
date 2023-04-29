package com.example.bob.data

import kotlinx.coroutines.flow.Flow

class OfflineNotesRepository(private val notesDao: NotesDao) :
    NotesRepository {
    override fun getAllNotesStream(): Flow<List<Note>> =
        notesDao.getAllNote()

    override fun getNotesStream(id: Int): Flow<Note?> =
        notesDao.getNote(id)

    override suspend fun insertNotes(note: Note) =
        notesDao.insert(note)

    override suspend fun deleteNotes(note: Note) =
        notesDao.delete(note)

    override suspend fun updateNotes(note: Note) =
        notesDao.update(note)
}