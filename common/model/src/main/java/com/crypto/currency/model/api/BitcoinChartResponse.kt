package com.crypto.currency.model.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BitcoinChartResponse(
    @Json(name = "description")
    val description: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "period")
    val period: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "unit")
    val unit: String,
    @Json(name = "values")
    val values: List<ValueResponse>
)

@JsonClass(generateAdapter = true)
data class ValueResponse(
    @Json(name = "x")
    val x: Int,
    @Json(name = "y")
    val y: Double
)