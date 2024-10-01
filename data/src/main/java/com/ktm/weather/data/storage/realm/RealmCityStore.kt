package com.ktm.weather.data.storage.realm

import com.ktm.weather.data.storage.CityStore
import com.ktm.weather.data.storage.realm.model.RealmCityDbObj
import com.ktm.weather.domain.entity.City
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Inject

class RealmCityStore @Inject constructor() : CityStore {

    private val configuration = RealmConfiguration.create(schema = setOf(RealmCityDbObj::class))

    override suspend fun saveCity(city: City) {
        with(Realm.open(configuration)) {
            val dbObj = RealmCityDbObj().apply {
                name = city.name
                country = city.country
            }
            write { copyToRealm(dbObj) }
            close()
        }
    }

    override suspend fun getCities(): List<City> {
        val result = arrayListOf<City>()
        with(Realm.open(configuration)) {
            val query = query(RealmCityDbObj::class).find()
            query.forEach {
                result.add(
                    0,
                    City(
                        id = it.id,
                        name = it.name,
                        country = it.country
                    )
                )
            }
            close()
        }
        return result
    }

    override suspend fun deleteCity(city: City) {
        with(Realm.open(configuration)) {
            write {
                val query = query(RealmCityDbObj::class, "id = $0", city.id ?: "")
                delete(query)
            }
            close()
        }
    }
}