package com.ktm.weather.presentation.util

object Const {

    const val BASE_URL: String = "https://api.openweathermap.org/data/2.5/"

    val DAYS_OF_WEEK: Array<String> = arrayOf(
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday"
    )

    val WEATHER_STATUS: Array<String> = arrayOf(
        "Thunderstorm",
        "Drizzle",
        "Rain",
        "Snow",
        "Atmosphere",
        "Clear",
        "Few Clouds",
        "Broken Clouds",
        "Cloud"
    )
}