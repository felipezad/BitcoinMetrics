package com.crypto.currency.di.storage

import com.crypto.currency.model.chart.BitcoinFilter

class InMemoryStorage : Storage<BitcoinFilter> {

    @Volatile
    private lateinit var lastData: BitcoinFilter

    override fun putData(param: BitcoinFilter): BitcoinFilter {
        lastData = param
        return lastData
    }

    override fun getData(): BitcoinFilter {
        return if (this::lastData.isInitialized) {
            lastData
        } else {
            BitcoinFilter.defaultFilter()
        }
    }
}