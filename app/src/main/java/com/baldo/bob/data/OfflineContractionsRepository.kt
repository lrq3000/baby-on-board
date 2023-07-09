package com.baldo.bob.data

import kotlinx.coroutines.flow.Flow

class OfflineContractionsRepository(private val contractionsDao: ContractionsDao) :
    ContactionsRepository {
    override fun getAllContractionsStream(): Flow<List<Contraction>> =
        contractionsDao.getAllContractions()

    override fun getContractionStream(id: Int): Flow<Contraction?> =
        contractionsDao.getContraction(id)

    override suspend fun insertContraction(contraction: Contraction) =
        contractionsDao.insert(contraction)

    override suspend fun deleteContraction(contraction: Contraction) =
        contractionsDao.delete(contraction)

    override suspend fun updateContraction(contraction: Contraction) =
        contractionsDao.update(contraction)
}