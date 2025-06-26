package com.example.ecosystemalpha.interactor

import com.example.ecosystemalpha.domain.model.BinInfo
import com.example.ecosystemalpha.domain.repository.BinRepository
import com.example.ecosystemalpha.domain.usecase.GetBinInfoUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

class GetBinInfoUseCaseImplTest {

    private lateinit var repository: BinRepository
    private lateinit var useCase: GetBinInfoUseCase

    @Before
    fun setup() {
        repository = mock()
        useCase = GetBinInfoUseCaseImpl(repository)
    }

    @Test
    fun `invoke should return bin info from repository`() = runTest {
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

        assertEquals(expectedBinInfo, actual)
        verify(repository).getBinInfo(testBin)
        verifyNoMoreInteractions(repository)
    }
}