package com.canda.ca.viewmodel

import com.canda.ca.data.Resource
import com.canda.ca.domain.model.WeatherResponse
import com.canda.ca.domain.usecase.currentweather.GetCurrentTimeWeatherUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetCurrentWeatherUseCase(
    private val testValue: Resource<WeatherResponse>?
) : GetCurrentTimeWeatherUseCase {
    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): Flow<Resource<WeatherResponse>> {
        return flow {
            delay(3000L)
            if (testValue == null)
                emit(Resource.Error("error"))
            else
                emit(testValue)
        }
    }

}