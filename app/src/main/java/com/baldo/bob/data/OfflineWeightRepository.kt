package com.baldo.bob.data

import kotlinx.coroutines.flow.Flow
import java.util.Date

class OfflineWeightRepository(private val weightDao: WeightDao) : WeightRepository {
    override fun getAllWeightStream(): Flow<List<Weight>> =
        weightDao.getAllWeight()

    override fun getWeightStream(date: Date): Flow<Weight?> =
        weightDao.getWeight(date)

    override suspend fun insertWeight(weight: Weight) =
        weightDao.insert(weight)

    override suspend fun updateWeight(weight: Weight) =
        weightDao.update(weight)

    override suspend fun deleteWeight(weight: Weight) =
        weightDao.delete(weight)
}