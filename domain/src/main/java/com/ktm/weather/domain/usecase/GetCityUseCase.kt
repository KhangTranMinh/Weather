package com.ktm.weather.domain.usecase

import com.ktm.weather.domain.entity.City
import com.ktm.weather.domain.repository.CityRepository
import com.ktm.weather.domain.repository.result.Error
import com.ktm.weather.domain.repository.result.Result

class GetCityUseCase(
    private val cityRepository: CityRepository
) {

    suspend fun saveCity(city: City) {
        cityRepository.saveCity(city)
    }

    suspend fun getCities(): Result<List<City>, Error> {
        return cityRepository.getCities()
    }

    suspend fun deleteCity(city: City) {
        cityRepository.deleteCity(city)
    }
}