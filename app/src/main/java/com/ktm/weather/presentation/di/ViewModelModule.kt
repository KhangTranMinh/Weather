package com.ktm.weather.presentation.di

import com.ktm.weather.data.repository.CityRepositoryImpl
import com.ktm.weather.data.repository.WeatherRepositoryImpl
import com.ktm.weather.data.storage.CityStore
import com.ktm.weather.data.storage.realm.RealmCityStore
import com.ktm.weather.domain.repository.CityRepository
import com.ktm.weather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    @Binds
    abstract fun bindCityRepository(impl: CityRepositoryImpl): CityRepository

    @Binds
    abstract fun bindCityStore(impl: RealmCityStore): CityStore
}