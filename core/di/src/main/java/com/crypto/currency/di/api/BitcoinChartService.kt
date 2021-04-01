package com.crypto.currency.di.api

import com.crypto.currency.model.api.BitcoinChartResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BitcoinChartService {

    @GET("{chartName}")
    suspend fun getChartBy(
        @Path("chartName") chartName: String,
        @Query("timespan") timespan: String,
        @Query("rollingAverage") rollingAverage: String
    ): BitcoinChartResponse

    //TODO add Query Parameters
}