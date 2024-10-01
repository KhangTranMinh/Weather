package com.ktm.weather.data.storage

import com.ktm.weather.domain.entity.City

interface CityStore {

    suspend fun saveCity(city: City)

    suspend fun getCities(): List<City>

    suspend fun deleteCity(city: City)
}