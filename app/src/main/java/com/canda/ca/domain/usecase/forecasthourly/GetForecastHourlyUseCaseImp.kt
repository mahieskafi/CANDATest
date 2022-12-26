package com.canda.ca.domain.usecase.forecasthourly

import com.canda.ca.BuildConfig
import com.canda.ca.data.Resource
import com.canda.ca.domain.Constants
import com.canda.ca.domain.model.WeatherListResponse
import com.canda.ca.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetForecastHourlyUseCaseImp(
    private val repository: WeatherRepository
) : GetForecastHourlyUseCase{
    override suspend fun getForecastHourlyList(
        latitude: Double,
        longitude: Double
    ): Flow<Resource<WeatherListResponse>> = flow {
        try {
            val response = repository.getForecastHourlyList(
                latitude,
                longitude,
                Constants.UNITS,
                BuildConfig.APP_KEY,
                8
            )
            if (response.isSuccessful)
                emit(Resource.Success(response.body()))
            else emit(Resource.Error(response.message()))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}