package com.crypto.currency.bitcoin

import com.crypto.currency.di.features.UseCaseProducer
import com.crypto.currency.model.ActionResult
import com.crypto.currency.model.graph.BitcoinChart
import javax.inject.Inject

class GetCharUseCase @Inject constructor(
    private val bitcoinChartRepository: BitcoinChartRepository
) : UseCaseProducer<ActionResult<BitcoinChart>> {

    override suspend fun execute(): ActionResult<ActionResult<BitcoinChart>> {
        TODO("Not yet implemented")
    }

    override val className: String
        get() = TODO("Not yet implemented")
}