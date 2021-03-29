package com.crypto.currency.di.api

import com.crypto.currency.model.api.GraphResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BitcoinService {

    @GET
    suspend fun getChartBy(@Path("chartName") chartName: String): GraphResponse

    //TODO add Query Parameters
}