package com.canda.ca.data.repository

import com.canda.ca.data.repository.datasource.WeatherRemoteDataSource
import com.canda.ca.domain.model.WeatherListResponse
import com.canda.ca.domain.model.WeatherResponse
import com.canda.ca.domain.repository.WeatherRepository
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val dataSource: WeatherRemoteDataSource,
) : WeatherRepository {
    override suspend fun getCurrentTimeWeather(
        latitude: Double,
        longitude: Double,
        units: String,
        appId: String
    ): Response<WeatherResponse> =
        dataSource.getCurrentTimeWeather(
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
        dataSource.getForecastHourlyList(
            latitude,
            longitude,
            units,
            appId,
            listCount
        )
}