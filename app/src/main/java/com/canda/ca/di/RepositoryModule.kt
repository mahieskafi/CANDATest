package com.canda.ca.di

import com.canda.ca.data.repository.WeatherRepositoryImp
import com.canda.ca.data.repository.datasource.WeatherRemoteDataSource
import com.canda.ca.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideProductRepository(
        dataSource: WeatherRemoteDataSource
    ): WeatherRepository =
        WeatherRepositoryImp(
            dataSource
        )
}