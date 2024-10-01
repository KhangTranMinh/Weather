package com.ktm.weather.data.network.data

import com.google.gson.GsonBuilder

open class BaseRequest {

    override fun toString(): String {
        return "${this::class.java.simpleName}: ${GsonBuilder().create().toJson(this)}"
    }
}

open class BaseResponse {

    override fun toString(): String {
        return "${this::class.java.simpleName}: ${GsonBuilder().create().toJson(this)}"
    }
}