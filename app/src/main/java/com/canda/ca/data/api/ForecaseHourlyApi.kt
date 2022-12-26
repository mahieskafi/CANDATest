package com.canda.ca.data.api

import com.canda.ca.domain.model.WeatherListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastHourlyApi {
    @GET("forecast")
    suspend fun getForecastHourlyList(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String,
        @Query("appid") appId: String,
        @Query("cnt") listCount: Int
    ): Response<WeatherListResponse>
}
