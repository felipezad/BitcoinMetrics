package com.crypto.currency.bitcoin

import com.crypto.currency.model.chart.BitcoinChart
import com.google.common.truth.Truth
import org.junit.Test

class BitcoinChartMapperTest {

    private val mapper = BitcoinChartMapper()

    @Test
    fun `Should map response object to model object correctly`() {
        val response = BitcoinChartFakeFactory.createFullBitcoinChartResponse()
        val expected = BitcoinChartFakeFactory.createFullBitcoinChart()
        val bitcoinObject = mapper.to(from = response)
        Truth.assertThat(bitcoinObject).isInstanceOf(BitcoinChart::class.java)
        Truth.assertThat(bitcoinObject.name).isEqualTo(expected.name)
        Truth.assertThat(bitcoinObject.description).isEqualTo(expected.description)
        Truth.assertThat(bitcoinObject.period).isEqualTo(expected.period)
        Truth.assertThat(bitcoinObject.status).isEqualTo(expected.status)
        Truth.assertThat(bitcoinObject.values.size).isEqualTo(expected.values.size)
    }

}