package com.crypto.currency.filters

import com.crypto.currency.di.features.UseCase
import com.crypto.currency.model.ActionResult
import com.crypto.currency.model.Failure
import com.crypto.currency.model.Success
import com.crypto.currency.model.chart.BitcoinFilter
import com.crypto.currency.ui.InvalidFilterBitcoinException
import javax.inject.Inject

class AddBitcoinFilterUseCase @Inject constructor(private val repository: FilterRepository) :
    UseCase<AddBitcoinFilterUseCase.Param, BitcoinFilter> {

    override suspend fun execute(param: Param): ActionResult<BitcoinFilter> {
        return try {
            if (param.bitcoinFilter == BitcoinFilter.emptyFilter) {
                throw InvalidFilterBitcoinException()
            }
            val result = repository.addToStorage(param.bitcoinFilter)
            Success(result)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    data class Param(val bitcoinFilter: BitcoinFilter)
}