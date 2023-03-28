package com.example.bob.data

import kotlinx.coroutines.flow.Flow

class OfflineInformationsRepository(private val informationsDao: InformationsDao) :
    InformationsRepository {
/*    override fun getAllInformationsStream(): Flow<List<Informations>> =
        informationsDao.getInformations()*/

    override fun getInformationsStream(id: Int): Flow<Informations?> =
        informationsDao.getInformations()

    override suspend fun insertInformations(informations: Informations) =
        informationsDao.insert(informations)

    override suspend fun deleteInformations(informations: Informations) =
        informationsDao.delete(informations)

    override suspend fun updateInformations(informations: Informations) =
        informationsDao.update(informations)
}