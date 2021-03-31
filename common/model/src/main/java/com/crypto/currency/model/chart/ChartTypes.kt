package com.crypto.currency.model.chart

enum class ChartTypes(val chartName: String) {
    TRANSACTIONS_PER_SECOND("transactions-per-second"),
    TOTAL_BITCOINS("total-bitcoins"),
    TRANSACTIONS_TOTAL("n-transactions-total"),
    MARKET_PRICE("market-price");

    companion object {
        fun from(chartNameString: String): ChartTypes {
            return try {
                values().find { it.chartName.contentEquals(chartNameString) } ?: TOTAL_BITCOINS
            } catch (_: Exception) {
                TOTAL_BITCOINS
            }
        }
    }
}