package com.crypto.currency.bitcoin

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.crypto.currency.model.Loading
import com.crypto.currency.model.Success
import com.crypto.currency.model.chart.BitcoinChart
import com.crypto.currency.model.chart.ChartTypes
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BitcoinChartViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var getChartByNameUseCase: GetChartByNameUseCase

    private lateinit var subject: BitcoinChartViewModel

    private lateinit var observerBitcoinCharts: Observer<BitcoinChart>

    private lateinit var observerLoadingState: Observer<Loading>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        subject = BitcoinChartViewModel(getChartByNameUseCase)
        observerBitcoinCharts = Observer { }
        subject.bitcoinChartData.observeForever(observerBitcoinCharts)
        mockkStatic(Log::class)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        subject.bitcoinChartData.removeObserver(observerBitcoinCharts)
        subject.loadingState.removeObserver(observerLoadingState)
    }

    @Test
    fun `When a API call was made should have only two loading states`() {
        runBlockingTest {
            coEvery {
                getChartByNameUseCase.execute(any())
            } returns Success(BitcoinChartFakeFactory.createFullBitcoinChart())

            val loadingStates = mutableListOf<Loading>()
            observerLoadingState = Observer<Loading> {
                loadingStates.add(it)
            }
            subject.loadingState.observeForever(observerLoadingState)

            subject.getCharByName(ChartTypes.TOTAL_BITCOINS)

            Truth.assertThat(loadingStates).hasSize(2)
            Truth.assertThat(loadingStates)
                .isEqualTo(listOf(Loading(true), Loading(false)))
        }
    }
}