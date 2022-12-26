package com.canda.ca.data.repository.datasourceImpl

import com.canda.ca.data.api.CurrentTimeApi
import com.canda.ca.data.api.ForecastHourlyApi
import com.canda.ca.data.repository.datasource.WeatherRemoteDataSource
import com.canda.ca.domain.model.WeatherListResponse
import com.canda.ca.domain.model.WeatherResponse
import retrofit2.Response
import javax.inject.Inject

class WeatherRemoteDataSourceImp @Inject constructor(
    private val currentTimeApi: CurrentTimeApi,
    private val forecastHourlyApi : ForecastHourlyApi
) : WeatherRemoteDataSource {
    override suspend fun getCurrentTimeWeather(
        latitude: Double,
        longitude: Double,
        units: String,
        appId: String
    ): Response<WeatherResponse> =
       currentTimeApi.getCurrentTimeWeather(
           latitude,
           longitude,
           units,
           appId
       )

    override suspend fun getForecastHourlyList(
        latitude: Double,
        longitude: Double,
        units: String,
        appId: String,
        listCount: Int
    ): Response<WeatherListResponse> =
        forecastHourlyApi.getForecastHourlyList(
            latitude,
            longitude,
            units,
            appId,
            listCount
        )

}