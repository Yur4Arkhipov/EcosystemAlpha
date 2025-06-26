package com.example.ecosystemalpha.data.remote.service

import com.example.ecosystemalpha.data.remote.dto.BinResponseDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface BinApiService {
    @GET("/{bin}")
    suspend fun getBinInfo(
        @Path("bin") bin: String,
        @Header("Accept-Version") version: String = "3"
    ) : BinResponseDto
}