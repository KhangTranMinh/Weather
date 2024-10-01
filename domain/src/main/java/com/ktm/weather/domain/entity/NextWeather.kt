package com.ktm.weather.domain.entity

class NextWeather(
    var dt: Long = 0,
    var temp: Double = 0.0,
    var minTemp: Double = 0.0,
    var maxTemp: Double = 0.0,
    var weatherId: Int = 0,
    var timestampStart: Long = 0,
    var timestampEnd: Long = 0,
)