package com.ktm.weather.data.network.data.common

import com.google.gson.annotations.SerializedName

class Weather(
    @SerializedName("id") val id: Int?,
    @SerializedName("main") val main: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("icon") val icon: String?,
)