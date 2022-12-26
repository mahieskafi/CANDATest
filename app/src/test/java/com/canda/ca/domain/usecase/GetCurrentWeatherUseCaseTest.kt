package com.canda.ca.domain.usecase

import com.canda.ca.FakeWeatherResponse
import com.canda.ca.MainCoroutineRule
import com.canda.ca.domain.FakeRepository
import com.canda.ca.domain.model.WeatherResponse
import com.canda.ca.domain.usecase.currentweather.GetCurrentTimeWeatherUseCaseImp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.math.abs

@ExperimentalCoroutinesApi
class GetCurrentWeatherUseCaseTest {

    private lateinit var weatherRepository: FakeRepository
    private lateinit var getCurrentTimeWeatherUseCase: GetCurrentTimeWeatherUseCaseImp

    private lateinit var weatherMock : WeatherResponse

    private val latitude = 44.34
    private val longitude = 10.99
    private val units = "metric"
    private val appId = "fakeId"

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        weatherMock = FakeWeatherResponse().makeWeather()

        weatherRepository = FakeRepository(weatherMock)
        getCurrentTimeWeatherUseCase = GetCurrentTimeWeatherUseCaseImp(weatherRepository)
    }

    @Test
    fun getWeather_getCurrentWeatherFromRemoteDataSource_success() = runTest {

        val weatherResponse =
            weatherRepository.getCurrentTimeWeather(latitude, longitude, units, appId)

        Assert.assertEquals(weatherResponse.isSuccessful, true)

        val result = weatherResponse.body()

        Assert.assertEquals(result, weatherMock)
        Assert.assertEquals(
            result!!.coordinationInfo.latitude,
            latitude,
            abs(result.coordinationInfo.latitude - latitude)
        )
    }

    @Test
    fun getWeather_getCurrentWeather_error() = runTest {
        weatherRepository.weatherResponse = null
        val weatherResponse =
            weatherRepository.getCurrentTimeWeather(latitude, longitude, units, appId)

        Assert.assertEquals(weatherResponse.isSuccessful, false)
        Assert.assertEquals(weatherResponse.code(), 1001)
    }
}