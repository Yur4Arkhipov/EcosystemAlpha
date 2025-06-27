package com.example.ecosystemalpha.ui

import app.cash.turbine.test
import com.example.ecosystemalpha.core.util.UiState
import com.example.ecosystemalpha.domain.model.BinInfo
import com.example.ecosystemalpha.domain.usecase.GetBinInfoUseCase
import com.example.ecosystemalpha.ui.viewmodel.BinScreenViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class BinScreenViewModelTest {

    private lateinit var useCase: GetBinInfoUseCase
    private  lateinit var viewModel: BinScreenViewModel

    @Before
    fun setup() {
        useCase = mock()
        viewModel = BinScreenViewModel(useCase)
    }

    @Test
    fun `loadBin should update binInfo state flow`() = runTest {
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

        whenever(useCase.invoke(testBin)).thenReturn(UiState.Success(expectedBinInfo))

        viewModel.binInfo.test {
            val init = awaitItem()
            assertEquals(UiState.Idle, init)

            viewModel.loadBin(testBin)
            val loading = awaitItem()
            assertEquals(UiState.Loading, loading)

            val updated = awaitItem()
            assertTrue(updated is UiState.Success)
            assertEquals(expectedBinInfo, (updated as UiState.Success).data)

            verify(useCase).invoke(testBin)
        }
    }
}