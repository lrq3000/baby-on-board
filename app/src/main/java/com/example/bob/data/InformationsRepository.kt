package com.example.bob.data

import kotlinx.coroutines.flow.Flow

interface InformationsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    /*fun getAllInformationsStream(): Flow<List<Informations>>*/

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getInformationsStream(id: Int): Flow<Informations?>

    /**
     * Insert item in the data source
     */
    suspend fun insertInformations(informations: Informations)

    /**
     * Delete item from the data source
     */
    suspend fun deleteInformations(informations: Informations)

    /**
     * Update item in the data source
     */
    suspend fun updateInformations(item: Informations)
}