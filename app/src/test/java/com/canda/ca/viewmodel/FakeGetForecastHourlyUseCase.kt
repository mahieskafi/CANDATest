package com.canda.ca.viewmodel

import com.canda.ca.data.Resource
import com.canda.ca.domain.model.WeatherListResponse
import com.canda.ca.domain.usecase.forecasthourly.GetForecastHourlyUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetForecastHourlyUseCase(
    private val testValue: Resource<WeatherListResponse>?
) : GetForecastHourlyUseCase {

    override suspend fun getForecastHourlyList(
        latitude: Double,
        longitude: Double
    ): Flow<Resource<WeatherListResponse>> {
        return flow {
            delay(3000L)
            if (testValue == null)
                emit(Resource.Error("error"))
            else
                emit(testValue)
        }
    }

}