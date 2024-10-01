package com.ktm.weather.domain.entity

class City(
    val id: String? = null,
    val name: String,
    val country: String
) {

    override fun toString(): String {
        return if (country.isBlank()) {
            name
        } else {
            "$name, $country"
        }
    }
}