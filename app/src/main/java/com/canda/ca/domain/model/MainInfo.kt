package com.canda.ca.domain.model

import com.google.gson.annotations.SerializedName

data class MainInfo(

    @SerializedName("main")
    val mainStatus: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("icon")
    val icon: String

)
