package com.ktm.weather.presentation.ui.main

import com.ktm.weather.domain.entity.City
import java.util.UUID

object MockDb {

    fun getMockCities(): List<City> {
        return arrayListOf(
            City(
                id = UUID.randomUUID().toString(),
                name = "Ho Chi Minh",
                country = "Viet Nam"
            ),
            City(
                id = UUID.randomUUID().toString(),
                name = "Ha Noi",
                country = "Viet Nam"
            ),
            City(
                id = UUID.randomUUID().toString(),
                name = "Houston",
                country = "Texas"
            ),
            City(
                id = UUID.randomUUID().toString(),
                name = "Los Angeles",
                country = "California"
            ),
            City(
                id = UUID.randomUUID().toString(),
                name = "Seattle",
                country = "Washington"
            ),
            City(
                id = UUID.randomUUID().toString(),
                name = "Beijing",
                country = "China"
            ),
            City(
                id = UUID.randomUUID().toString(),
                name = "Shanghai",
                country = "China"
            ),
            City(
                id = UUID.randomUUID().toString(),
                name = "Seoul",
                country = "Korea"
            ),
            City(
                id = UUID.randomUUID().toString(),
                name = "Incheon",
                country = "Korea"
            ),
            City(
                id = UUID.randomUUID().toString(),
                name = "Jeju",
                country = "Korea"
            ),
            City(
                id = UUID.randomUUID().toString(),
                name = "Tokyo",
                country = "Japan"
            ),
        )
    }
}