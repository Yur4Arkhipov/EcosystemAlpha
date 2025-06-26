package com.example.ecosystemalpha.data.remote.dto

data class BinResponseDto(
    val number: CardNumberDto? = null,
    val scheme: String? = null,
    val type: String? = null,
    val brand: String?  = null,
    val prepaid: String? = null,
    val country: CardCountryDto? = null,
    val bank: CardBankDto? = null
)
