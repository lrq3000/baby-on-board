package com.baldo.bob.data

import kotlinx.coroutines.flow.Flow
import java.util.Date

interface WeightRepository {

    fun getAllWeightStream(): Flow<List<Weight>>

    fun getWeightStream(date: Date): Flow<Weight?>

    suspend fun insertWeight(weight: Weight)

    suspend fun deleteWeight(weight: Weight)

    suspend fun updateWeight(weight: Weight)
}