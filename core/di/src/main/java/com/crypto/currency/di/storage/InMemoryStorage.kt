package com.crypto.currency.di.storage

import com.crypto.currency.model.chart.BitcoinFilter

class InMemoryStorage : Storage<BitcoinFilter> {

    private lateinit var lastData: BitcoinFilter

    override fun putData(param: BitcoinFilter) {
        lastData = param
    }

    override fun getData(): BitcoinFilter {
        return if (this::lastData.isInitialized) {
            lastData
        } else {
            BitcoinFilter.defaultFilter()
        }
    }
}