package com.crypto.currency.ui

import com.crypto.currency.model.chart.BitcoinChart

typealias Height = Int
typealias Value = String

class BitcoinChartAdapter(private val bitcoinChart: BitcoinChart) {

    private val heightConstant = 50

    fun getLastFiveValues(): List<Pair<Height, Value>> {
        var latestValue = 0f
        var previousHeight = heightConstant
        return bitcoinChart.values.takeLast(5).map {
            val currentValue = it.y.toFloat()
            val pair = calculateRelativeHeight(
                currentValue = currentValue,
                previousValue = latestValue,
                previousHeight = previousHeight
            ) to it.y.toBigDecimal()
                .toPlainString()
            latestValue = currentValue
            previousHeight = pair.first
            pair
        }
    }

    private fun calculateRelativeHeight(
        currentValue: Float,
        previousValue: Float,
        previousHeight: Int
    ): Int {
        if (currentValue == 0f) {
            return 0
        }
        if (previousValue == 0f) {
            return heightConstant
        }
        val nextHeight = (previousHeight * currentValue) / previousValue
        return if (currentValue > previousValue) {
            nextHeight + heightConstant
        } else {
            nextHeight - heightConstant
        }.toInt()
    }
}