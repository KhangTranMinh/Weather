package com.ktm.weather.domain.entity

class CurrentWeather(
    val temp: Double = 0.0,
    val humidity: Int = 0,
    val description: String = "",
    val main: String = "",
    val weatherId: Int = 0,
    val windDeg: Double = 0.0,
    val windSpeed: Double = 0.0,
    val storeTimestamp: Long = 0,
)