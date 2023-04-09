package com.example.bob.data

import androidx.room.*
import androidx.room.Insert
import kotlinx.coroutines.flow.Flow

@Dao
interface InformationsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(informations: Informations)

    @Update
    suspend fun update(informations: Informations)

    @Delete
    suspend fun delete(informations: Informations)

    @Query("SELECT * from informations")
    fun getInformations(): Flow<Informations>
}