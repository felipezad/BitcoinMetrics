package com.crypto.currency.di.storage

import com.crypto.currency.model.chart.BitcoinFilter
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class InMemoryStorageTest {

    lateinit var subject: Storage<BitcoinFilter>

    @Before
    fun setup() {
        subject = InMemoryStorage()
    }


    @Test
    fun `When no data is inserted in the storage should return default object`() {
        Truth.assertThat(subject.getData()).isEqualTo(BitcoinFilter.defaultFilter())
    }

    @Test
    fun `When data is inserted in the storage should return expected object`() {
        val param = BitcoinFilter(1, 1)
        subject.putData(param)
        Truth.assertThat(subject.getData()).isEqualTo(param)
    }
}