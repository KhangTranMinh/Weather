package com.ktm.weather.data.repository

import com.ktm.weather.data.storage.CityStore
import com.ktm.weather.domain.entity.City
import com.ktm.weather.domain.repository.CityRepository
import com.ktm.weather.domain.repository.result.Error
import com.ktm.weather.domain.repository.result.Result
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val cityStore: CityStore
) : CityRepository {

    override suspend fun saveCity(city: City) {
        cityStore.saveCity(city)
    }

    override suspend fun getCities(): Result<List<City>, Error> {
        return Result.Success(cityStore.getCities())
    }

    override suspend fun deleteCity(city: City) {
        cityStore.deleteCity(city)
    }
}