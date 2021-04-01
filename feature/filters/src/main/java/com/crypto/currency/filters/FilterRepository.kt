package com.crypto.currency.filters

import com.crypto.currency.di.storage.InMemoryStorage
import com.crypto.currency.model.chart.BitcoinFilter
import javax.inject.Inject

class FilterRepository @Inject constructor(private val storage: InMemoryStorage) {

    fun addToStorage(param: BitcoinFilter) {
        storage.putData(param)
    }
}


