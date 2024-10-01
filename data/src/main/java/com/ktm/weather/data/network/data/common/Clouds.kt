package com.ktm.weather.data.network.data.common

import com.google.gson.annotations.SerializedName

class Clouds(
    @SerializedName("all") val all: Int?,
)