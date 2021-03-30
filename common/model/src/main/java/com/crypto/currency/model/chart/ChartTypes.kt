package com.crypto.currency.model.chart

enum class ChartTypes(val graphName: String) {
    TRANSACTIONS_PER_SECOND("transactions-per-second"),
    TOTAL_BITCOINS("total-bitcoins"),
    TRANSACTIONS_TOTAL("n-transactions-total"),
    MARKET_PRICE("market-price")
}