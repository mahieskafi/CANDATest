package com.canda.ca.di

import android.app.Application
import com.canda.ca.data.api.CurrentTimeApi
import com.canda.ca.data.api.ForecastHourlyApi
import com.canda.ca.data.repository.datasource.WeatherRemoteDataSource
import com.canda.ca.data.repository.datasourceImpl.WeatherRemoteDataSourceImp
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideWeatherRemoteDataSource(
        currentTimeApi: CurrentTimeApi,
        forecastHourlyApi: ForecastHourlyApi
    ): WeatherRemoteDataSource =
        WeatherRemoteDataSourceImp(
            currentTimeApi,
            forecastHourlyApi
        )

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }
}