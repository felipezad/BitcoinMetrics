package com.crypto.currency.model.chart

data class BitcoinFilter(val timeSpan: Int, val rollingAverage: Int) {

    companion object {
        fun defaultFilter(): BitcoinFilter {
            return BitcoinFilter(timeSpan = 5, rollingAverage = 8)
        }
    }
}
