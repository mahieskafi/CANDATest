package com.canda.ca.di

import com.canda.ca.domain.repository.WeatherRepository
import com.canda.ca.domain.usecase.currentweather.GetCurrentTimeWeatherUseCase
import com.canda.ca.domain.usecase.currentweather.GetCurrentTimeWeatherUseCaseImp
import com.canda.ca.domain.usecase.forecasthourly.GetForecastHourlyUseCase
import com.canda.ca.domain.usecase.forecasthourly.GetForecastHourlyUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetCurrentTimeWeatherUseCase(
        weatherRepository: WeatherRepository
    ): GetCurrentTimeWeatherUseCase =
        GetCurrentTimeWeatherUseCaseImp(
            weatherRepository
        )

    @Provides
    fun provideGetForecastHourlyListUseCase(
        weatherRepository: WeatherRepository
    ): GetForecastHourlyUseCase =
        GetForecastHourlyUseCaseImp(
            weatherRepository
        )
}