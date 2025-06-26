package com.example.ecosystemalpha.data.remote.mappers

import com.example.ecosystemalpha.data.remote.dto.BinResponseDto
import com.example.ecosystemalpha.domain.model.BinInfo

fun BinResponseDto.toDomain(): BinInfo {
    return BinInfo(
        numberLength = (number?.length ?: "unknown").toString(),
        luhn = (number?.luhn ?: "unknown").toString(),
        scheme = scheme ?: "unknown",
        type = type ?: "unknown",
        brand = brand ?: "unknown",
        prepaid = (prepaid ?: "unknown").toString(),
        countryNumeric = country?.numeric ?: "unknown",
        countryAlpha2 = country?.alpha2 ?: "unknown",
        countryName = country?.name ?: "unknown",
        countryEmoji = country?.emoji ?: "unknown",
        countryCurrency = country?.currency ?: "unknown",
        countryLatitude = (country?.latitude ?: "unknown").toString(),
        countryLongitude = (country?.longitude ?: "unknown").toString(),
        bankName = bank?.name ?: "unknown",
        bankUrl = bank?.url ?: "unknown",
        bankPhone = bank?.phone ?: "unknown",
        bankCity = bank?.city ?: "unknown",
    )
}