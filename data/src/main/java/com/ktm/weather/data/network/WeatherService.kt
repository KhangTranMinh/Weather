package com.ktm.weather.data.network

import com.ktm.weather.data.network.data.FetchCurrentWeatherResponse
import com.ktm.weather.data.network.data.FetchNextWeathersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    suspend fun fetchCurrentWeather(
        @Query("q") q: String,
        @Query("units") units: String = ApiConfig.METRIC,
        @Query("lang") lang: String = "",
        @Query("appid") appId: String = ApiConfig.API_KEY
    ): FetchCurrentWeatherResponse?

    @GET("forecast/daily")
    suspend fun fetchNextWeathers(
        @Query("q") q: String,
        @Query("units") units: String = ApiConfig.METRIC,
        @Query("lang") lang: String = "",
        @Query("cnt") cnt: Int = ApiConfig.NEXT_WEATHER_COUNT,
        @Query("appid") appId: String = ApiConfig.API_KEY
    ): FetchNextWeathersResponse?
}