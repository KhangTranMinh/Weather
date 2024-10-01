package com.ktm.weather.data.network.data.common

import com.google.gson.annotations.SerializedName

class Wind(
    @SerializedName("speed") val speed: Double?,
    @SerializedName("deg") val deg: Double?,
)