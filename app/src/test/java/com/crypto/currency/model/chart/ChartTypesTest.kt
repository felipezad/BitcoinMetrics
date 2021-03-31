package com.crypto.currency.model.chart

import com.crypto.currency.model.chart.ChartTypes.*
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class ChartTypesTest(private val literalName: String, private val enumType: ChartTypes) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf("transactions-per-second", TRANSACTIONS_PER_SECOND),
                arrayOf("total-bitcoins", TOTAL_BITCOINS),
                arrayOf("n-transactions-total", TRANSACTIONS_TOTAL),
                arrayOf("market-price", MARKET_PRICE)
            )
        }
    }

    @Test
    fun `should guarantee the charts names didn't change in the enum`() {
        assertEquals(literalName, enumType.chartName)
    }

    @Test
    fun `should cast string name to chart type`() {
        assertEquals(enumType, ChartTypes.from(literalName))
    }
}