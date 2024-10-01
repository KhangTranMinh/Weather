package com.ktm.weather.domain.repository

import com.ktm.weather.domain.entity.CurrentWeather
import com.ktm.weather.domain.entity.NextWeather
import com.ktm.weather.domain.repository.result.Error
import com.ktm.weather.domain.repository.result.Result

interface WeatherRepository {

    suspend fun fetchCurrentWeather(q: String): Result<CurrentWeather, Error>

    suspend fun fetchNextWeathers(q: String): Result<List<NextWeather>, Error>
}