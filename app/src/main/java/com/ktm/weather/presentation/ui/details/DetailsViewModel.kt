package com.ktm.weather.presentation.ui.details

import androidx.lifecycle.viewModelScope
import com.ktm.weather.domain.entity.CurrentWeather
import com.ktm.weather.domain.entity.NextWeather
import com.ktm.weather.domain.repository.WeatherRepository
import com.ktm.weather.domain.repository.result.Error
import com.ktm.weather.domain.repository.result.Result
import com.ktm.weather.domain.usecase.FetchWeatherUseCase
import com.ktm.weather.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    weatherRepository: WeatherRepository
) : BaseViewModel() {

    private val fetchWeatherUseCase = FetchWeatherUseCase(weatherRepository)

    private val _detailsUiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Default)
    val detailsUiState: StateFlow<DetailsUiState> = _detailsUiState

    fun fetchData(q: String) {
        viewModelScope.launch {
            val currentWeatherResult: Result<CurrentWeather, Error>
            val nextWeathersResult: Result<List<NextWeather>, Error>
            withContext(Dispatchers.Default) {
                currentWeatherResult = fetchWeatherUseCase.fetchCurrentWeather(q)
                nextWeathersResult = fetchWeatherUseCase.fetchNextWeathers(q)
            }
            if (currentWeatherResult is Result.Success && nextWeathersResult is Result.Success) {
                _detailsUiState.value =
                    DetailsUiState.FetchCurrentWeatherSuccess(currentWeatherResult.data)
                _detailsUiState.value =
                    DetailsUiState.FetchFiveDaysWeatherSuccess(nextWeathersResult.data)
            } else {
                _detailsUiState.value = DetailsUiState.Error()
            }
        }
    }
}

sealed class DetailsUiState {
    data object Default : DetailsUiState()

    data class FetchCurrentWeatherSuccess(
        val currentWeather: CurrentWeather
    ) : DetailsUiState()

    data class FetchFiveDaysWeatherSuccess(
        val nextWeathers: List<NextWeather>
    ) : DetailsUiState()

    data class Error(
        val errorCode: Int? = null, val throwable: Throwable? = null
    ) : DetailsUiState()
}