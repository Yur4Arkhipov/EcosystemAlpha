package com.example.ecosystemalpha.data.local.mappers

import com.example.ecosystemalpha.data.local.BinQueryEntity
import com.example.ecosystemalpha.domain.model.BinInfo

fun BinInfo.toEntity(bin: String): BinQueryEntity = BinQueryEntity(
    bin = bin,
    numberLength = numberLength,
    luhn = luhn,
    scheme = scheme,
    type = type,
    brand = brand,
    prepaid = prepaid,
    countryNumeric = countryNumeric,
    countryAlpha2 = countryAlpha2,
    countryName = countryName,
    countryEmoji = countryEmoji,
    countryCurrency = countryCurrency,
    countryLatitude = countryLatitude,
    countryLongitude = countryLongitude,
    bankName = bankName,
    bankUrl = bankUrl,
    bankPhone = bankPhone,
    bankCity = bankCity
)
