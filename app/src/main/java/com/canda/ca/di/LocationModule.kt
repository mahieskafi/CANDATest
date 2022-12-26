package com.canda.ca.di

import com.canda.ca.data.location.TestAppLocationTracker
import com.canda.ca.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun provideLocationTracker(
        defaultLocationTracker: TestAppLocationTracker
    ): LocationTracker
}