package com.ktm.weather.data.network.data

import com.google.gson.annotations.SerializedName
import com.ktm.weather.data.network.data.common.Coord
import com.ktm.weather.data.network.data.common.Weather

class FetchNextWeathersRequest(
    @SerializedName("q") val q: String,
    @SerializedName("units") val units: String? = null,
    @SerializedName("lang") val lang: String? = null,
) : BaseRequest()

class FetchNextWeathersResponse(
    @SerializedName("city") val city: City?,
    @SerializedName("cod") val cod: String?,
    @SerializedName("message") val message: Double?,
    @SerializedName("cnt") val cnt: Int?,
    @SerializedName("list") val list: List<ItemDay>?,
) : BaseResponse() {

    class ItemDay(
        @SerializedName("dt") val dt: Long?,
        @SerializedName("sunrise") val sunrise: Long?,
        @SerializedName("sunset") val sunset: Long?,
        @SerializedName("temp") val temp: Temp?,
        @SerializedName("feels_like") val feelLike: FeelLike?,
        @SerializedName("pressure") val pressure: Int?,
        @SerializedName("humidity") val humidity: Int?,
        @SerializedName("weather") val weather: List<Weather>?,
        @SerializedName("speed") val speed: Double?,
        @SerializedName("deg") val deg: Int?,
        @SerializedName("gust") val gust: Double?,
        @SerializedName("clouds") val clouds: Double?,
        @SerializedName("pop") val pop: Double?,
        @SerializedName("rain") val rain: Double?,
    ) {

        class Temp(
            @SerializedName("day") val day: Double?,
            @SerializedName("min") val min: Double?,
            @SerializedName("max") val max: Double?,
            @SerializedName("night") val night: Double?,
            @SerializedName("eve") val eve: Double?,
            @SerializedName("morn") val morn: Double?,
        )

        class FeelLike(
            @SerializedName("day") val day: Double?,
            @SerializedName("night") val night: Double?,
            @SerializedName("eve") val eve: Double?,
            @SerializedName("morn") val morn: Double?,
        )
    }

    class City(
        @SerializedName("id") val id: Int?,
        @SerializedName("name") val name: String?,
        @SerializedName("coord") val coord: Coord?,
        @SerializedName("country") val country: String?,
        @SerializedName("population") val population: Int?,
    )
}