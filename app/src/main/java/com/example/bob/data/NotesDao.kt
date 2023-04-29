package com.example.bob.data

import androidx.room.*
import androidx.room.Insert
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * from notes WHERE id = :id")
    fun getNote(id: Int): Flow<Note>

    @Query("SELECT * from notes")
    fun getAllNote(): Flow<List<Note>>

}