package com.example.bob.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ContractionsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contraction: Contraction)

    @Update
    suspend fun update(contraction: Contraction)

    @Delete
    suspend fun delete(contraction: Contraction)

    @Query("SELECT * from contractions WHERE id = :id")
    fun getContraction(id: Int): Flow<Contraction>

    @Query("SELECT * from contractions")
    fun getAllContractions(): Flow<List<Contraction>>
}