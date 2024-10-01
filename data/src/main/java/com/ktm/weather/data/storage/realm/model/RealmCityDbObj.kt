package com.ktm.weather.data.storage.realm.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.UUID

class RealmCityDbObj : RealmObject {

    @PrimaryKey
    var id: String = UUID.randomUUID().toString()

    var name: String = ""

    var country: String = ""
}