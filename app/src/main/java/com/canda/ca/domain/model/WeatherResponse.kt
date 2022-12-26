package com.canda.ca.domain.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(

    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("main")
    val temperatureInfo: TemperatureInfo,

    @SerializedName("weather")
    val mainInfo: List<MainInfo>,

    @SerializedName("dt")
    val dateTime: Long,

    @SerializedName("sys")
    val internalInfo: InternalInfo,

    @SerializedName("coord")
    val coordinationInfo: CoordinationInfo

)
