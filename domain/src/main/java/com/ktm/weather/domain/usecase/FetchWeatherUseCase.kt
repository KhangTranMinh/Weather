package com.ktm.weather.domain.usecase

import com.ktm.weather.domain.entity.CurrentWeather
import com.ktm.weather.domain.entity.NextWeather
import com.ktm.weather.domain.repository.WeatherRepository
import com.ktm.weather.domain.repository.result.Error
import com.ktm.weather.domain.repository.result.Result

class FetchWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {

    suspend fun fetchCurrentWeather(q: String): Result<CurrentWeather, Error> {
        return weatherRepository.fetchCurrentWeather(q)
    }

    suspend fun fetchNextWeathers(q: String): Result<List<NextWeather>, Error> {
        return weatherRepository.fetchNextWeathers(q)
    }
}