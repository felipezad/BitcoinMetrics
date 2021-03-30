package com.crypto.currency.model.chart

import com.crypto.currency.model.chart.ChartTypes.*
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class ChartTypesTest(val literalName: String, val enumType: ChartTypes) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf("transactions-per-second", TRANSACTIONS_PER_SECOND),
                arrayOf("total-bitcoins", TOTAL_BITCOINS),
                arrayOf("n-transactions-total", TRANSACTIONS_TOTAL),
                arrayOf("market-price", MARKET_PRICE),
            )
        }
    }

    @Test
    fun test() {
        assertEquals(literalName, enumType.graphName)
    }
}