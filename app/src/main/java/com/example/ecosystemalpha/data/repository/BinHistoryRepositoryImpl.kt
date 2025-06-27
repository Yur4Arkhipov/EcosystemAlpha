package com.example.ecosystemalpha.data.repository

import com.example.ecosystemalpha.data.local.BinQueriesDao
import com.example.ecosystemalpha.data.local.BinQueryEntity
import com.example.ecosystemalpha.domain.repository.BinHistoryRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class BinHistoryRepositoryImpl @Inject constructor(
    private val dao: BinQueriesDao
) : BinHistoryRepository {

    override fun getAllQueries(): Flow<List<BinQueryEntity>> = dao.getAllQueries()
    override suspend fun addQuery(query: BinQueryEntity) = dao.insert(query)
}