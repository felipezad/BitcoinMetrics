package com.crypto.currency.bitcoin

import com.crypto.currency.di.api.BitcoinChartService
import com.crypto.currency.di.features.Repository
import com.crypto.currency.model.ActionResult
import com.crypto.currency.model.Failure
import com.crypto.currency.model.Success
import com.crypto.currency.model.chart.BitcoinChart
import javax.inject.Inject

class BitcoinChartRepository @Inject constructor(
    private val bitcoinChartService: BitcoinChartService,
    private val bitcoinCharMapper: BitcoinChartMapper
) : Repository<String, BitcoinChart> {

    override suspend fun getElementsFromApi(chartName: String): ActionResult<BitcoinChart> {
        return try {
            val responseApi = bitcoinChartService.getChartBy(chartName)
            val data = bitcoinCharMapper.to(responseApi)
            Success(data)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun getElementsFromDatabase(): ActionResult<BitcoinChart> {
        TODO("Not yet implemented")
    }
}