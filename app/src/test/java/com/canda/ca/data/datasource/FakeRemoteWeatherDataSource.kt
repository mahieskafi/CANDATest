package com.canda.ca.data.datasource

import com.canda.ca.data.repository.datasource.WeatherRemoteDataSource
import com.canda.ca.domain.model.WeatherListResponse
import com.canda.ca.domain.model.WeatherResponse
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response

class FakeRemoteWeatherDataSource(
    var weatherResponse: WeatherResponse? = null,
    var weatherListResponse: WeatherListResponse? = null
) : WeatherRemoteDataSource {

    override suspend fun getCurrentTimeWeather(
        latitude: Double,
        longitude: Double,
        units: String,
        appId: String
    ): Response<WeatherResponse> {
        weatherResponse?.let {
            return Response.success(it)
        }
        return Response.error(
            1001
            , ResponseBody.create(
                MediaType.parse("UTF-8"),
                "response not found"
            )
        )
    }

    override suspend fun getForecastHourlyList(
        latitude: Double,
        longitude: Double,
        units: String,
        appId: String,
        listCount: Int
    ): Response<WeatherListResponse> {
        weatherListResponse?.let {
            return Response.success(it)
        }
        return Response.error(
            1001
            , ResponseBody.create(
                MediaType.parse("UTF-8"),
                "response not found"
            )
        )
    }
}