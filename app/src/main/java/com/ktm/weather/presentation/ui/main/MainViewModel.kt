package com.ktm.weather.presentation.ui.main

import androidx.lifecycle.viewModelScope
import com.ktm.weather.domain.entity.City
import com.ktm.weather.domain.repository.CityRepository
import com.ktm.weather.domain.repository.result.Result
import com.ktm.weather.domain.usecase.GetCityUseCase
import com.ktm.weather.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    cityRepository: CityRepository
) : BaseViewModel() {

    private val getCityUseCase = GetCityUseCase(cityRepository)

    private val _mainUiState = MutableStateFlow<MainUiState>(MainUiState.Default)
    val mainUiState: StateFlow<MainUiState> = _mainUiState

    fun getCities() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                getCityUseCase.getCities()
            }
            when (result) {
                is Result.Success -> {
                    _mainUiState.value = MainUiState.GetCitySuccess(result.data)
                }

                is Result.Error -> {
                    _mainUiState.value = MainUiState.Error()
                }
            }
        }
    }

    fun deleteCity(city: City) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getCityUseCase.deleteCity(city)
            }
            getCities()
        }
    }

    fun saveCity(city: City) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getCityUseCase.saveCity(city)
            }
            getCities()
        }
    }

    private suspend fun addCitiesIfDbEmpty() {
        val result = getCityUseCase.getCities()
        if (result is Result.Success) {
            if (result.data.isEmpty()) {
                MockDb.getMockCities().forEach {
                    getCityUseCase.saveCity(city = it)
                }
            }
        }
    }
}

sealed class MainUiState {
    data object Default : MainUiState()

    data class GetCitySuccess(val cities: List<City>) : MainUiState()

    data class Error(
        val errorCode: Int? = null, val throwable: Throwable? = null
    ) : MainUiState()
}