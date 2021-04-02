package com.crypto.currency.ui

import com.crypto.currency.model.chart.BitcoinChart

class BitcoinChartAdapter(private val bitcoinChart: BitcoinChart) {

    fun getLastFiveValues(): List<Pair<Int, String>> {
        return bitcoinChart.values.takeLast(5).mapIndexed { index, it ->
            index to it.y.toBigDecimal().toPlainString()
        }
    }
}