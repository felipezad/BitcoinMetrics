package com.crypto.currency.bitcoin

import com.crypto.currency.model.chart.BitcoinChart
import com.crypto.currency.model.chart.Value
import kotlin.random.Random

object BitcoinChartFakeFactory {

    fun createEmptyBitcoinChart() =
        BitcoinChart("description", "name", "period", "status", "unit", listOf())

    fun createFullBitcoinChart() =
        BitcoinChart("description", "name", "period", "status", "unit", createListOfValues())

    private fun createListOfValues(): List<Value> {
        val mutableList = mutableListOf<Value>()
        val random: () -> Int = { Random.nextInt(0, 999) }
        for (i in 0 until 100) {
            mutableList.add(Value(random(), random().toDouble()))
        }
        return mutableList
    }
}