package com.ktm.weather.data.network.api

import com.ktm.weather.data.network.ApiConfig
import com.ktm.weather.data.network.WeatherService
import com.ktm.weather.data.network.data.FetchCurrentWeatherRequest
import com.ktm.weather.data.network.data.FetchCurrentWeatherResponse
import com.ktm.weather.data.network.mock.MockResponse
import javax.inject.Inject

class FetchCurrentWeatherApi @Inject constructor(
    private val weatherService: WeatherService
) : BaseApi<FetchCurrentWeatherRequest, FetchCurrentWeatherResponse>() {

    override suspend fun execute(
        request: FetchCurrentWeatherRequest
    ): FetchCurrentWeatherResponse? {
        return runCatching {
            android.util.Log.d("Weather", request.toString())
            weatherService.fetchCurrentWeather(q = request.q)
        }.getOrElse {
            if (ApiConfig.USE_MOCK_RESPONSE) {
                MockResponse.fetchCurrentWeatherResponse()
            } else {
                null
            }
        }
    }
}