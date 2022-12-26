package com.canda.ca.domain.usecase.forecasthourly

import com.canda.ca.data.Resource
import com.canda.ca.domain.model.WeatherListResponse
import kotlinx.coroutines.flow.Flow

interface GetForecastHourlyUseCase {
    suspend fun getForecastHourlyList(
        latitude: Double,
        longitude: Double
    ): Flow<Resource<WeatherListResponse>>
}