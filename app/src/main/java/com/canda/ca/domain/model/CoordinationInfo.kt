package com.canda.ca.domain.model

import com.google.gson.annotations.SerializedName

data class CoordinationInfo(
    @SerializedName("lon")
    val longitude: Double,

    @SerializedName("lat")
    val latitude: Double
)
