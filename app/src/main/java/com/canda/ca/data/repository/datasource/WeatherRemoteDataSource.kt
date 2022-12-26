package com.canda.ca.data.repository.datasource

import com.canda.ca.domain.model.WeatherListResponse
import com.canda.ca.domain.model.WeatherResponse
import retrofit2.Response

interface WeatherRemoteDataSource {
    suspend fun getCurrentTimeWeather(
        latitude: Double,
        longitude: Double,
        units: String,
        appId: String
    ): Response<WeatherResponse>

    suspend fun getForecastHourlyList(
        latitude: Double,
        longitude: Double,
        units: String,
        appId: String,
        listCount: Int
    ): Response<WeatherListResponse>
}