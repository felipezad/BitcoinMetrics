package com.crypto.currency.filters

import com.crypto.currency.di.features.UseCase
import com.crypto.currency.model.ActionResult
import com.crypto.currency.model.Failure
import com.crypto.currency.model.Success
import com.crypto.currency.model.chart.BitcoinFilter
import javax.inject.Inject

class AddBitcoinFilterUseCase @Inject constructor(private val repository: FilterRepository) :
    UseCase<BitcoinFilter, BitcoinFilter> {

    override suspend fun execute(param: BitcoinFilter): ActionResult<BitcoinFilter> {
        return try {
            val result = repository.addToStorage(param)
            Success(result)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}