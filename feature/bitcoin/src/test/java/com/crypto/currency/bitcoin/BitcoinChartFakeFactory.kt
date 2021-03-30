package com.crypto.currency.bitcoin

import com.crypto.currency.model.api.BitcoinChartResponse
import com.crypto.currency.model.api.ValueResponse
import com.crypto.currency.model.chart.BitcoinChart
import com.crypto.currency.model.chart.Value
import kotlin.random.Random

object BitcoinChartFakeFactory {

    fun createEmptyBitcoinChart() =
        BitcoinChart("description", "name", "period", "status", "unit", listOf())

    fun createFullBitcoinChart() =
        BitcoinChart("description", "name", "period", "status", "unit", createListOfValues())

    fun createEmptyBitcoinChartResponse() =
        BitcoinChartResponse("description", "name", "period", "status", "unit", listOf())

    fun createFullBitcoinChartResponse() =
        BitcoinChartResponse(
            "description",
            "name",
            "period",
            "status",
            "unit",
            createListOfValuesResponse()
        )

    private fun createListOfValues(): List<Value> {
        val mutableList = mutableListOf<Value>()
        val random: () -> Int = { Random.nextInt(0, 999) }
        for (i in 0 until 100) {
            mutableList.add(Value(random(), random().toDouble()))
        }
        return mutableList
    }

    private fun createListOfValuesResponse(): List<ValueResponse> {
        val random: () -> Int = { Random.nextInt(0, 999) }
        val mutableList = mutableListOf<ValueResponse>()
        for (i in 0 until 100) {
            mutableList.add(ValueResponse(random(), random().toDouble()))
        }
        return mutableList
    }
}