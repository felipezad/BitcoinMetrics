package com.crypto.currency.model.chart

data class BitcoinChart(
    val description: String,
    val name: String,
    val period: String,
    val status: String,
    val unit: String,
    val values: List<Value>
)

data class Value(
    val x: Int,
    val y: Double
)
