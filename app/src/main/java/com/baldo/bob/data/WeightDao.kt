package com.baldo.bob.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface WeightDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weight: Weight)

    @Update
    suspend fun update(weight: Weight)

    @Delete
    suspend fun delete(weight: Weight)

    @Query("SELECT * from weights WHERE date = :date")
    fun getWeight(date: Date): Flow<Weight>

    @Query("SELECT * from weights")
    fun getAllWeight(): Flow<List<Weight>>
}