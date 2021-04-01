package com.crypto.currency.filters

import com.crypto.currency.model.Failure
import com.crypto.currency.model.Success
import com.crypto.currency.model.chart.BitcoinFilter
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class AddBitcoinFilterUseCaseTest {
    private val testDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var mockFilterRepository: FilterRepository

    private lateinit var subject: AddBitcoinFilterUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        subject = AddBitcoinFilterUseCase(mockFilterRepository)
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
                mockFilterRepository.addToStorage(any())
            } returns mockk()

            val param = AddBitcoinFilterUseCase.Param(BitcoinFilter.defaultFilter())
            val result = subject.execute(param)

            Truth.assertThat(result).isInstanceOf(Success::class.java)
        }
    }

    @Test
    fun `when repository returns exception then the result should be failure`() {
        runBlockingTest {
            coEvery {
                mockFilterRepository.addToStorage(any())
            } throws Exception()

            val param = AddBitcoinFilterUseCase.Param(BitcoinFilter.defaultFilter())
            val result = subject.execute(param)

            Truth.assertThat(result).isInstanceOf(Failure::class.java)
        }
    }
}