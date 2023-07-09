package com.baldo.bob.data

import kotlinx.coroutines.flow.Flow

interface ContactionsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllContractionsStream(): Flow<List<Contraction>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getContractionStream(id: Int): Flow<Contraction?>

    /**
     * Insert item in the data source
     */
    suspend fun insertContraction(contraction: Contraction)

    /**
     * Delete item from the data source
     */
    suspend fun deleteContraction(contraction: Contraction)

    /**
     * Update item in the data source
     */
    suspend fun updateContraction(contraction: Contraction)
}