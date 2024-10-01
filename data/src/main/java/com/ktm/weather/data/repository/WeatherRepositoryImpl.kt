package com.ktm.weather.data.repository

import com.ktm.weather.data.network.ErrorCode
import com.ktm.weather.data.network.api.FetchCurrentWeatherApi
import com.ktm.weather.data.network.api.FetchNextWeathersApi
import com.ktm.weather.data.network.data.FetchCurrentWeatherRequest
import com.ktm.weather.data.network.data.FetchNextWeathersRequest
import com.ktm.weather.domain.entity.CurrentWeather
import com.ktm.weather.domain.entity.NextWeather
import com.ktm.weather.domain.repository.WeatherRepository
import com.ktm.weather.domain.repository.result.Error
import com.ktm.weather.domain.repository.result.Result
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val fetchCurrentWeatherApi: FetchCurrentWeatherApi,
    private val fetchNextWeathersApi: FetchNextWeathersApi
) : WeatherRepository {

    override suspend fun fetchCurrentWeather(q: String): Result<CurrentWeather, Error> {
        val response = fetchCurrentWeatherApi.execute(
            FetchCurrentWeatherRequest(q)
        )
        return if (response == null) {
            Result.Error(Error.ApiError(ErrorCode.UNKNOWN))
        } else {
            val currentWeather = CurrentWeather(
                temp = response.main?.temp ?: 0.0,
                humidity = response.main?.humidity ?: 0,
                description = response.weather?.get(0)?.description ?: "",
                main = response.weather?.get(0)?.main ?: "",
                weatherId = response.weather?.get(0)?.id ?: 0,
                windDeg = response.wind?.deg ?: 0.0,
                windSpeed = response.wind?.speed ?: 0.0
            )
            Result.Success(currentWeather)
        }
    }

    override suspend fun fetchNextWeathers(q: String): Result<List<NextWeather>, Error> {
        val response = fetchNextWeathersApi.execute(
            FetchNextWeathersRequest(q)
        )
        return if (response == null) {
            Result.Error(Error.ApiError(ErrorCode.UNKNOWN))
        } else {
            val nextWeathers = arrayListOf<NextWeather>()
            response.list?.forEach { item ->
                nextWeathers.add(
                    NextWeather(
                        dt = item.dt ?: 0L,
                        temp = item.temp?.day ?: 0.0,
                        minTemp = item.temp?.min ?: 0.0,
                        maxTemp = item.temp?.max ?: 0.0,
                        weatherId = item.weather?.get(0)?.id ?: 0,
                    )
                )
            }
            Result.Success(nextWeathers)
        }
    }
}