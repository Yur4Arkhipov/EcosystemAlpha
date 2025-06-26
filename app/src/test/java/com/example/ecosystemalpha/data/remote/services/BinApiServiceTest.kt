package com.example.ecosystemalpha.data.remote.services

import com.example.ecosystemalpha.data.remote.dto.CardBankDto
import com.example.ecosystemalpha.data.remote.dto.CardCountryDto
import com.example.ecosystemalpha.data.remote.dto.CardNumberDto
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BinApiServiceTest {

    private lateinit var service: BinApiService

    @Before
    fun setup() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(BinApiService::class.java)
    }

    @Test
    fun testGetBinInfo() = runTest { 
        val response = service.getBinInfo("45717360")

        assertNotNull(response)

        assertEquals(CardNumberDto(), response.number)
        assertEquals("visa", response.scheme)
        assertEquals("debit", response.type)
        assertEquals("Visa Classic/Dankort", response.brand)
        assertEquals(null, response.prepaid)
        assertEquals(
            CardCountryDto(
                numeric = "208",
                alpha2 = "DK",
                name = "Denmark",
                emoji = "\uD83C\uDDE9\uD83C\uDDF0",
                currency = "DKK",
                latitude = 56,
                longitude = 10
            ), response.country
        )
        assertEquals(CardBankDto(name = "Jyske Bank A/S"), response.bank)
    }
}