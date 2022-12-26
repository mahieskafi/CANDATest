package com.canda.ca.domain.model

import com.google.gson.annotations.SerializedName

data class WeatherListResponse(
    @SerializedName("list")
    val list: List<WeatherResponse>,
)

