package com.example.ecosystemalpha.data.repository

import com.example.ecosystemalpha.data.remote.dto.BinResponseDto
import com.example.ecosystemalpha.data.remote.dto.CardBankDto
import com.example.ecosystemalpha.data.remote.dto.CardCountryDto
import com.example.ecosystemalpha.data.remote.dto.CardNumberDto
import com.example.ecosystemalpha.data.remote.services.BinApiService
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class BinRepositoryImplTest {

    private lateinit var api: BinApiService
    private lateinit var repository: BinRepositoryImpl

    @Before
    fun setup() {
        api = mock()
        repository = BinRepositoryImpl(api)
    }

    @Test
    fun `getBinInfo should return mapped domain object`() = runTest {
        val bin = "45717360"
        val dto = BinResponseDto(
            number = CardNumberDto(),
            scheme = "visa",
            type = "debit",
            brand = "Visa Classic/Dankort",
            country = CardCountryDto(
                numeric = "208",
                alpha2 = "DK",
                name = "Denmark",
                emoji = "\uD83C\uDDE9\uD83C\uDDF0",
                currency = "DKK",
                latitude = 56,
                longitude = 10
            ),
            bank = CardBankDto(name = "Jyske Bank A/S")
        )

        whenever(api.getBinInfo(bin)).thenReturn(dto)

        val result = repository.getBinInfo(bin)

        assertEquals("unknown", result.numberLength)
        assertEquals("unknown", result.luhn)
        assertEquals("visa", result.scheme)
        assertEquals("debit", result.type)
        assertEquals("Visa Classic/Dankort", result.brand)
        assertEquals("unknown", result.prepaid)
        assertEquals("208", result.countryNumeric)
        assertEquals("DK", result.countryAlpha2)
        assertEquals("Denmark", result.countryName)
        assertEquals("\uD83C\uDDE9\uD83C\uDDF0", result.countryEmoji)
        assertEquals("DKK", result.countryCurrency)
        assertEquals("56", result.countryLatitude)
        assertEquals("10", result.countryLongitude)
        assertEquals("Jyske Bank A/S", result.bankName)
        assertEquals("unknown", result.bankUrl)
        assertEquals("unknown", result.bankCity)
        assertEquals("unknown", result.bankPhone)

        verify(api).getBinInfo(bin)
    }
}