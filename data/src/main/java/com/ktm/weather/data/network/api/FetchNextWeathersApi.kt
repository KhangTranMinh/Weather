package com.ktm.weather.data.network.api

import com.ktm.weather.data.network.ApiConfig
import com.ktm.weather.data.network.WeatherService
import com.ktm.weather.data.network.data.FetchNextWeathersRequest
import com.ktm.weather.data.network.data.FetchNextWeathersResponse
import com.ktm.weather.data.network.mock.MockResponse
import javax.inject.Inject

class FetchNextWeathersApi @Inject constructor(
    private val weatherService: WeatherService
) : BaseApi<FetchNextWeathersRequest, FetchNextWeathersResponse>() {

    override suspend fun execute(
        request: FetchNextWeathersRequest
    ): FetchNextWeathersResponse? {
        return runCatching {
            android.util.Log.d("Weather", request.toString())
            weatherService.fetchNextWeathers(q = request.q)
        }.getOrElse {
            if (ApiConfig.USE_MOCK_RESPONSE) {
                MockResponse.fetchNextWeathersResponse()
            } else {
                null
            }
        }
    }
}