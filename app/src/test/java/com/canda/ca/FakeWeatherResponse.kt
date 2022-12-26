package com.canda.ca

import com.canda.ca.domain.model.*

class FakeWeatherResponse {
     fun makeWeather(): WeatherResponse {
        val temperatureInfo = TemperatureInfo(
            temperature = 6.14,
            feelingTemperature = 6.14,
            minTemperature = .03,
            maxTemperature = 9.68,
            humidity = 97
        )
        val mainInfo = mutableListOf(
            MainInfo(
                mainStatus = "Rain",
                description = "moderate rain",
                icon = "10d"
            )
        )

        val internalInfo = InternalInfo(
            country = "DE",
            sunrise = 1661834187,
            sunset = 1661882248
        )
        val coordinationInfo = CoordinationInfo(
            latitude = 44.34,
            longitude = 10.99
        )
        return WeatherResponse(
            id = 1,
            name = "Berlin",
            temperatureInfo = temperatureInfo,
            dateTime = 1661882400,
            mainInfo = mainInfo,
            internalInfo = internalInfo,
            coordinationInfo = coordinationInfo
        )
    }

    fun makeWeatherList():WeatherListResponse{
        return WeatherListResponse(mutableListOf(makeWeather()))
    }
}