package com.canda.ca.domain.usecase.currentweather

import com.canda.ca.data.Resource
import com.canda.ca.domain.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface GetCurrentTimeWeatherUseCase {
    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): Flow<Resource<WeatherResponse>>
}