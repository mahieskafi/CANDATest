package com.canda.ca.domain

import com.canda.ca.data.datasource.FakeRemoteWeatherDataSource
import com.canda.ca.domain.model.WeatherListResponse
import com.canda.ca.domain.model.WeatherResponse
import com.canda.ca.domain.repository.WeatherRepository
import retrofit2.Response

class FakeRepository(
    var weatherResponse: WeatherResponse? = null,
    var weatherListResponse: WeatherListResponse? = null
) : WeatherRepository {

    private lateinit var fakeRemoteDataSource: FakeRemoteWeatherDataSource

    override suspend fun getCurrentTimeWeather(
        latitude: Double,
        longitude: Double,
        units: String,
        appId: String
    ): Response<WeatherResponse> {
        fakeRemoteDataSource = FakeRemoteWeatherDataSource(weatherResponse)
        return fakeRemoteDataSource.getCurrentTimeWeather(latitude, longitude, units, appId)
    }

    override suspend fun getForecastHourlyList(
        latitude: Double,
        longitude: Double,
        units: String,
        appId: String,
        listCount: Int
    ): Response<WeatherListResponse> {
        fakeRemoteDataSource = FakeRemoteWeatherDataSource(weatherListResponse = weatherListResponse)
        return fakeRemoteDataSource.getForecastHourlyList(latitude, longitude, units, appId,listCount)
    }
}