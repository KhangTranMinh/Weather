package com.ktm.weather.data.network.data

import com.google.gson.annotations.SerializedName
import com.ktm.weather.data.network.data.common.Clouds
import com.ktm.weather.data.network.data.common.Coord
import com.ktm.weather.data.network.data.common.Main
import com.ktm.weather.data.network.data.common.Weather
import com.ktm.weather.data.network.data.common.Wind

class FetchCurrentWeatherRequest(
    @SerializedName("q") val q: String,
    @SerializedName("units") val units: String? = null,
    @SerializedName("lang") val lang: String? = null,
) : BaseRequest()

class FetchCurrentWeatherResponse(
    @SerializedName("coord") val coord: Coord?,
    @SerializedName("weather") val weather: List<Weather>?,
    @SerializedName("base") val base: String?,
    @SerializedName("main") val main: Main?,
    @SerializedName("wind") val wind: Wind?,
    @SerializedName("clouds") val clouds: Clouds?,
    @SerializedName("dt") var dt: Int?,
    @SerializedName("sys") val sys: Sys?,
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("cod") val cod: Int?,
) : BaseResponse() {

    class Sys(
        @SerializedName("country") val country: String?,
        @SerializedName("sunrise") val sunrise: Int?,
        @SerializedName("sunset") val sunset: Int?,
    )
}