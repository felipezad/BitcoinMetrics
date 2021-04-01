package com.crypto.currency.filters

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.crypto.currency.model.Loading
import com.crypto.currency.model.Success
import com.crypto.currency.model.chart.BitcoinFilter
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import io.mockk.slot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FilterViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var addBitcoinFilterUseCase: AddBitcoinFilterUseCase

    private lateinit var subject: FilterViewModel

    private lateinit var observerBitcoinFilter: Observer<BitcoinFilter>
    private lateinit var observerLoadingState: Observer<Loading>


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        subject = FilterViewModel(addBitcoinFilterUseCase)
        observerBitcoinFilter = Observer {}
        subject.filterSaved.observeForever(observerBitcoinFilter)
        mockkStatic(Log::class)
    }

    @After
    fun destroy() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        subject.filterSaved.removeObserver(observerBitcoinFilter)
        subject.loadingState.removeObserver(observerLoadingState)
    }

    @Test
    fun `When filter is send from the user should keep the same values`() {
        val param = BitcoinFilter.defaultFilter()
        val slot = slot<BitcoinFilter>()
        coEvery {
            addBitcoinFilterUseCase.execute(capture(slot))
        } returns Success(param)

        val loadingStates = mutableListOf<Loading>()
        observerLoadingState = Observer<Loading> {
            loadingStates.add(it)
        }
        subject.loadingState.observeForever(observerLoadingState)

        subject.addFilters(param)


        assertThat(slot.captured).isEqualTo(param)
        assertThat(subject.filterSaved.value).isEqualTo(param)
        assertThat(loadingStates).hasSize(2)
        assertThat(loadingStates)
            .isEqualTo(listOf(Loading(true), Loading(false)))


    }
}