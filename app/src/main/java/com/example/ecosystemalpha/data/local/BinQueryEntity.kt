package com.example.ecosystemalpha.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "queries")
data class BinQueryEntity(
    @PrimaryKey val bin: String,
    val numberLength: String,
    val luhn: String,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: String,
    val countryNumeric: String,
    val countryAlpha2: String,
    val countryName: String,
    val countryEmoji: String,
    val countryCurrency: String,
    val countryLatitude: String,
    val countryLongitude: String,
    val bankName: String,
    val bankUrl: String,
    val bankPhone: String,
    val bankCity: String,
)