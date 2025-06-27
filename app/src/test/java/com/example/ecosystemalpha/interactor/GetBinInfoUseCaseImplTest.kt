package com.example.ecosystemalpha.interactor

import com.example.ecosystemalpha.core.util.UiState
import com.example.ecosystemalpha.data.remote.dto.BinResponseDto
import com.example.ecosystemalpha.domain.model.BinInfo
import com.example.ecosystemalpha.domain.repository.BinRepository
import com.example.ecosystemalpha.domain.usecase.GetBinInfoUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class GetBinInfoUseCaseImplTest {

    private lateinit var repository: BinRepository
    private lateinit var useCase: GetBinInfoUseCase

    @Before
    fun setup() {
        repository = mock()
        useCase = GetBinInfoUseCaseImpl(repository)
    }

    @Test
    fun `invoke should return UiState Success from repository`() = runTest {
        val testBin = "45717360"
        val expectedBinInfo = BinInfo(
            numberLength = "unknown",
            luhn = "unknown",
            scheme = "visa",
            type = "debit",
            brand = "Visa Classic/Dankort",
            prepaid = "unknown",
            countryNumeric = "208",
            countryAlpha2 = "DK",
            countryName = "Denmark",
            countryEmoji = "\uD83C\uDDE9\uD83C\uDDF0",
            countryCurrency = "DKK",
            countryLatitude = "56",
            countryLongitude = "10",
            bankName = "Jyske Bank A/S",
            bankUrl = "unknown",
            bankPhone = "unknown",
            bankCity = "unknown",
        )

        whenever(repository.getBinInfo(testBin)).thenReturn(expectedBinInfo)

        val actual = useCase(testBin)

        assertTrue(actual is UiState.Success<*>)
        assertEquals(expectedBinInfo, (actual as UiState.Success<*>).data)
        verify(repository).getBinInfo(testBin)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `invoke should return UiState Error for network issue`() = runTest {
        whenever(repository.getBinInfo(any())).thenAnswer {
            throw IOException("No internet")
        }

        val result = useCase("123456")

        assertTrue(result is UiState.Error)
        assertEquals("Network unavailable", (result as UiState.Error).message)
    }


    @Test
    fun `invoke should return UiState Error for http exception`() = runTest {
        val response = Response.error<BinResponseDto>(
            404,
            "Not Found".toResponseBody("application/json".toMediaType())
        )
        val httpException = HttpException(response)

        whenever(repository.getBinInfo(any())).thenThrow(httpException)

        val result = useCase("123456")

        assertTrue(result is UiState.Error)
        assertTrue((result as UiState.Error).message.contains("Server error code: 404"))
    }

    @Test
    fun `invoke should return UiState Error for unknown exception`() = runTest {
        whenever(repository.getBinInfo(any())).thenThrow(RuntimeException("Boom"))

        val result = useCase("123456")

        assertTrue(result is UiState.Error)
        assertEquals("Undefined error", (result as UiState.Error).message)
    }
}