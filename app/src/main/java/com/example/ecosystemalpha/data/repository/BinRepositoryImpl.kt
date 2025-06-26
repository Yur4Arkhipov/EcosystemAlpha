package com.example.ecosystemalpha.data.repository

import com.example.ecosystemalpha.data.remote.mappers.toDomain
import com.example.ecosystemalpha.data.remote.services.BinApiService
import com.example.ecosystemalpha.domain.model.BinInfo
import com.example.ecosystemalpha.domain.repository.BinRepository
import javax.inject.Inject

class BinRepositoryImpl @Inject constructor(
    private val api: BinApiService
) : BinRepository {

    override suspend fun getBinInfo(bin: String): BinInfo {
        return api.getBinInfo(bin).toDomain()
    }
}