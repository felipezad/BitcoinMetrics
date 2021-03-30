package com.crypto.currency.bitcoin

import com.crypto.currency.di.features.UseCase
import com.crypto.currency.model.ActionResult
import com.crypto.currency.model.chart.BitcoinChart
import com.crypto.currency.model.chart.ChartTypes
import javax.inject.Inject

class GetChartByNameUseCase @Inject constructor(
    private val bitcoinChartRepository: BitcoinChartRepository
) : UseCase<GetChartByNameUseCase.Param, BitcoinChart> {

    override suspend fun execute(param: Param): ActionResult<BitcoinChart> {
        return bitcoinChartRepository.getElementsFromApi(param.chart.graphName)
    }

    data class Param(val chart: ChartTypes)
}