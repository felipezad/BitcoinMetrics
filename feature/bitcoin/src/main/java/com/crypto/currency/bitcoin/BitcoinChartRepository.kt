package com.crypto.currency.bitcoin

import com.crypto.currency.di.api.BitcoinChartService
import com.crypto.currency.di.features.Repository
import com.crypto.currency.di.storage.InMemoryStorage
import com.crypto.currency.model.ActionResult
import com.crypto.currency.model.Failure
import com.crypto.currency.model.Success
import com.crypto.currency.model.api.BitcoinMetricsQueryParam
import com.crypto.currency.model.chart.BitcoinChart
import com.crypto.currency.model.chart.BitcoinFilter
import javax.inject.Inject

class BitcoinChartRepository @Inject constructor(
    private val bitcoinChartService: BitcoinChartService,
    private val bitcoinCharMapper: BitcoinChartMapper,
    private val storage: InMemoryStorage
) : Repository<String, BitcoinChart> {

    override suspend fun getElementsFromApi(param: String): ActionResult<BitcoinChart> {
        return try {
            val filter: BitcoinFilter = storage.getData()
            val responseApi =
                bitcoinChartService.getChartBy(
                    chartName = param,
                    timespan = "${filter.timeSpan}${BitcoinMetricsQueryParam.WEEKS.suffix}",
                    rollingAverage = "${filter.rollingAverage}${BitcoinMetricsQueryParam.HOURS.suffix}"
                )
            val data = bitcoinCharMapper.to(responseApi)
            Success(data)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}