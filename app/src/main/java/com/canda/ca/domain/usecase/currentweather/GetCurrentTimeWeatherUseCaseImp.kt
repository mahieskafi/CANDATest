package com.canda.ca.domain.usecase.currentweather

import com.canda.ca.BuildConfig
import com.canda.ca.data.Resource
import com.canda.ca.domain.Constants.UNITS
import com.canda.ca.domain.model.WeatherResponse
import com.canda.ca.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCurrentTimeWeatherUseCaseImp @Inject constructor(
    private val repository: WeatherRepository
) : GetCurrentTimeWeatherUseCase {

    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): Flow<Resource<WeatherResponse>> = flow {
        try {
            val response = repository.getCurrentTimeWeather(
                latitude,
                longitude,
                UNITS,
                BuildConfig.APP_KEY
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