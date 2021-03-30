package com.crypto.currency.bitcoin

import com.crypto.currency.di.mapper.Mapper
import com.crypto.currency.model.api.BitcoinChartResponse
import com.crypto.currency.model.chart.BitcoinChart
import com.crypto.currency.model.chart.Value
import javax.inject.Inject

class BitcoinChartMapper @Inject constructor() : Mapper<BitcoinChartResponse, BitcoinChart> {

    override fun to(from: BitcoinChartResponse): BitcoinChart {
        return from.run {
            BitcoinChart(
                description = description,
                name = name,
                period = period,
                status = status,
                unit = unit,
                values = values.map { Value(it.x, it.y) }
            )
        }
    }
}