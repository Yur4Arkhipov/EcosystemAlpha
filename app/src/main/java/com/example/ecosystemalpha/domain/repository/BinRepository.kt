package com.example.ecosystemalpha.domain.repository

import com.example.ecosystemalpha.domain.model.BinInfo

interface BinRepository {
    suspend fun getBinInfo(bin: String): BinInfo
}