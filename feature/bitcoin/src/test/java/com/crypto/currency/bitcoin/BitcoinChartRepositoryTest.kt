package com.crypto.currency.bitcoin

import com.crypto.currency.bitcoin.BitcoinChartFakeFactory.createFullBitcoinChart
import com.crypto.currency.bitcoin.BitcoinChartFakeFactory.createFullBitcoinChartResponse
import com.crypto.currency.di.api.BitcoinChartService
import com.crypto.currency.di.storage.InMemoryStorage
import com.crypto.currency.model.api.BitcoinChartResponse
import com.crypto.currency.model.api.BitcoinMetricsQueryParam
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertTrue
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
class BitcoinChartRepositoryTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @MockK
    private lateinit var mapper: BitcoinChartMapper

    @MockK
    private lateinit var bitcoinChartService: BitcoinChartService

    private lateinit var bitcoinChartRepository: BitcoinChartRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        bitcoinChartRepository =
            BitcoinChartRepository(bitcoinChartService, mapper, InMemoryStorage())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `When user requests the chart to the service should map to domain object successfully`() {
        runBlockingTest {
            mockkConstructor(BitcoinChartResponse::class)

            val slot = slot<BitcoinChartResponse>()
            val slotServiceQueryWeeks = slot<String>()
            val slotServiceQueryHours = slot<String>()
            coEvery {
                bitcoinChartService.getChartBy(
                    any(),
                    capture(slotServiceQueryWeeks),
                    capture(slotServiceQueryHours)
                )
            } returns createFullBitcoinChartResponse()


            every {
                mapper.to(capture(slot))
            } returns createFullBitcoinChart()

            bitcoinChartRepository.getElementsFromApi("any")

            coVerify(exactly = 1) {
                bitcoinChartService.getChartBy(any(), any(), any())
            }

            assertTrue(slot.isCaptured)
            verify(exactly = 1) {
                mapper.to(slot.captured)
            }
            assertThat(slotServiceQueryHours.captured).contains(BitcoinMetricsQueryParam.HOURS.suffix)
            assertThat(slotServiceQueryWeeks.captured).contains(BitcoinMetricsQueryParam.WEEKS.suffix)
        }
    }
}

