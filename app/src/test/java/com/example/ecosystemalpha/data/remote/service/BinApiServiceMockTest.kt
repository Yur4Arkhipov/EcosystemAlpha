package com.example.ecosystemalpha.data.remote.service

import com.example.ecosystemalpha.data.remote.dto.CardBankDto
import com.example.ecosystemalpha.data.remote.dto.CardCountryDto
import com.example.ecosystemalpha.data.remote.dto.CardNumberDto
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BinApiServiceMockTest {

    private lateinit var server: MockWebServer
    private lateinit var api: BinApiService

    @Before
    fun setupAndStartServer() {
        server = MockWebServer()
        server.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(server.url("/someUrl/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(BinApiService::class.java)
    }

    @Test
    fun testGetBinInfo() = runTest {
        val mockResponse = """
            {
                "number": {},
                "scheme": "visa",
                "type": "debit",
                "brand": "Visa Classic/Dankort",
                "country": {
                    "numeric": "208",
                    "alpha2": "DK",
                    "name": "Denmark",
                    "emoji": "🇩🇰",
                    "currency": "DKK",
                    "latitude": 56,
                    "longitude": 10
                },
                "bank": {
                    "name": "Jyske Bank A/S"
                }
            }
        """.trimIndent()

        server.enqueue(
            MockResponse()
                .setBody(mockResponse)
                .addHeader("Content-Type", "application/json")
        )

        val binApiServiceResult = api.getBinInfo("45717360")

        val request = server.takeRequest()

        assertEquals("/45717360", request.path)
        assertEquals("3", request.headers["Accept-Version"])
        assertEquals("GET", request.method)

        assertEquals(CardNumberDto(), binApiServiceResult.number)
        assertEquals("visa", binApiServiceResult.scheme)
        assertEquals("debit", binApiServiceResult.type)
        assertEquals("Visa Classic/Dankort", binApiServiceResult.brand)
        assertEquals(null, binApiServiceResult.prepaid)
        assertEquals(
            CardCountryDto(
                numeric = "208",
                alpha2 = "DK",
                name = "Denmark",
                emoji = "\uD83C\uDDE9\uD83C\uDDF0",
                currency = "DKK",
                latitude = 56,
                longitude = 10
            ), binApiServiceResult.country
        )
        assertEquals(CardBankDto(name = "Jyske Bank A/S"), binApiServiceResult.bank)
    }

    @After
    fun shutdownServer() {
        server.shutdown()
    }
}