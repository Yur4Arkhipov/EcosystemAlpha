package com.example.ecosystemalpha.domain.repository

import com.example.ecosystemalpha.data.local.BinQueryEntity
import kotlinx.coroutines.flow.Flow

interface BinHistoryRepository {
    fun getAllQueries(): Flow<List<BinQueryEntity>>
    suspend fun addQuery(query: BinQueryEntity)
}