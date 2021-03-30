package com.crypto.currency.bitcoin

import com.crypto.currency.model.Failure
import com.crypto.currency.model.Success
import com.crypto.currency.model.chart.ChartTypes
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetChartByNameUseCaseTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var mockBitcoinChartRepository: BitcoinChartRepository

    private lateinit var getGetChartByNameUseCase: GetChartByNameUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        getGetChartByNameUseCase = GetChartByNameUseCase(mockBitcoinChartRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when repository returns data then the result should be success`() {
        runBlockingTest {
            coEvery {
                mockBitcoinChartRepository.getElementsFromApi(any())
            } returns Success(mockk())

            val param = GetChartByNameUseCase.Param(ChartTypes.TRANSACTIONS_PER_SECOND)
            val result = getGetChartByNameUseCase.execute(param)

            Assert.assertEquals(true, result is Success)
        }
    }

    @Test
    fun `when repository returns exception then the result should be failure`() {
        runBlockingTest {
            coEvery {
                mockBitcoinChartRepository.getElementsFromApi(any())
            } returns Failure(mockk())

            val param = GetChartByNameUseCase.Param(ChartTypes.TRANSACTIONS_PER_SECOND)
            val result = getGetChartByNameUseCase.execute(param)

            Assert.assertEquals(true, result is Failure)
        }
    }
}