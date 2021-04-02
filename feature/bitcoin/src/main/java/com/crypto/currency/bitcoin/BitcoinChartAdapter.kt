package com.crypto.currency.bitcoin

import com.crypto.currency.di.mapper.EpochFormatter.toMonthDayDateFormat
import com.crypto.currency.di.mapper.EpochFormatter.toYearMonthDayDateFormat
import com.crypto.currency.model.chart.BitcoinChart

typealias Height = Int
typealias Value = String
typealias Date = String

class BitcoinChartAdapter(private val bitcoinChart: BitcoinChart) {

    private val heightConstant = 50

    fun getLastFiveValues(): List<Triple<Height, Value, Date>> {
        var latestValue = 0f
        var previousHeight = heightConstant
        return bitcoinChart.values
            .distinctBy { toYearMonthDayDateFormat(it.x.toLong()) }
            .takeLast(5).map {
                val currentValue = it.y.toFloat()
                val height = calculateRelativeHeight(
                    currentValue = currentValue,
                    previousValue = latestValue,
                    previousHeight = previousHeight
                )
                val valueOfChart = it.y.toInt().toString()
                val dateOfChart = toMonthDayDateFormat(it.x.toLong())
                val triple = Triple(height, valueOfChart, dateOfChart)
                latestValue = currentValue
                previousHeight = height
                triple
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