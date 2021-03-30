package com.crypto.currency.di.api

import com.crypto.currency.model.api.BitcoinChartResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BitcoinChartService {

    @GET("{chartName}")
    suspend fun getChartBy(@Path("chartName") chartName: String): BitcoinChartResponse

    //TODO add Query Parameters
}