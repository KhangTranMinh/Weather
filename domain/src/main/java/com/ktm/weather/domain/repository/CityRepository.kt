package com.ktm.weather.domain.repository

import com.ktm.weather.domain.entity.City
import com.ktm.weather.domain.repository.result.Error
import com.ktm.weather.domain.repository.result.Result

interface CityRepository {

    suspend fun saveCity(city: City)

    suspend fun getCities(): Result<List<City>, Error>

    suspend fun deleteCity(city: City)
}