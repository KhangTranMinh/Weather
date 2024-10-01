package com.ktm.weather.data.network.api

import com.ktm.weather.data.network.data.BaseRequest
import com.ktm.weather.data.network.data.BaseResponse


abstract class BaseApi<Request : BaseRequest, Response : BaseResponse> {

    abstract suspend fun execute(request: Request): Response?
}