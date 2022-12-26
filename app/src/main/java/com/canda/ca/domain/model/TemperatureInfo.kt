package com.canda.ca.domain.model

import com.google.gson.annotations.SerializedName

data class TemperatureInfo(

    @SerializedName("temp")
    val temperature: Double,

    @SerializedName("feels_like")
    val feelingTemperature: Double,

    @SerializedName("temp_min")
    val minTemperature: Double,

    @SerializedName("temp_max")
    val maxTemperature: Double,

    @SerializedName("humidity")
    val humidity: Int
)
