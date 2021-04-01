package com.crypto.currency.filters

import com.crypto.currency.di.storage.InMemoryStorage
import com.crypto.currency.model.chart.BitcoinFilter
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.spyk
import org.junit.Before
import org.junit.Test

class FilterRepositoryTest {

    private lateinit var subject: FilterRepository

    var storage: InMemoryStorage = spyk()

    @Before
    fun setup() {
        subject = FilterRepository(storage)
    }

    @Test
    fun `When add filter to storage should return the same value`() {
        val addToStorage = subject.addToStorage(BitcoinFilter.defaultFilter())
        Truth.assertThat(addToStorage).isEqualTo(BitcoinFilter.defaultFilter())
    }

    @Test(expected = Exception::class)
    fun `When add filter to storage and exception occurs should throws exception`() {
        every {
            storage.putData(any())
        } throws Exception()

        val addToStorage = subject.addToStorage(BitcoinFilter.defaultFilter())
        Truth.assertThat(addToStorage).isEqualTo(BitcoinFilter.defaultFilter())
    }
}