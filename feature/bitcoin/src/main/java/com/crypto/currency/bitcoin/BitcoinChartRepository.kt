package com.crypto.currency.bitcoin

import com.crypto.currency.di.features.Repository
import com.crypto.currency.model.ActionResult
import com.crypto.currency.model.graph.BitcoinChart
import javax.inject.Inject

class BitcoinChartRepository @Inject constructor() : Repository<BitcoinChart> {

    override suspend fun insertDataIntoRoom(data: BitcoinChart): ActionResult<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun getElementsFromApi(page: Int): ActionResult<List<BitcoinChart>> {
        TODO("Not yet implemented")
    }

    override suspend fun getElementsFromDatabase(): ActionResult<List<BitcoinChart>> {
        TODO("Not yet implemented")
    }
}