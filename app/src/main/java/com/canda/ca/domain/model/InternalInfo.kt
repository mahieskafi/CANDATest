package com.canda.ca.domain.model

import com.google.gson.annotations.SerializedName

data class InternalInfo(

    @SerializedName("country")
    val country: String,

    @SerializedName("sunrise")
    val sunrise: Long,

    @SerializedName("sunset")
    val sunset: Long
)
