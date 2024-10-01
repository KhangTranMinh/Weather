package com.ktm.weather.data.network.mock

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ktm.weather.data.network.data.FetchCurrentWeatherResponse
import com.ktm.weather.data.network.data.FetchNextWeathersResponse

object MockResponse {

    private val gson: Gson = GsonBuilder().create()

    fun fetchCurrentWeatherResponse(): FetchCurrentWeatherResponse? {
        return gson.fromJson(
            "{\"coord\":{\"lon\":-0.1257,\"lat\":51.5085},\"weather\":[{\"id\":502,\"main\":\"Rain\",\"description\":\"heavy intensity rain\",\"icon\":\"10d\"}],\"base\":\"stations\",\"main\":{\"temp\":12.6,\"feels_like\":12.27,\"temp_min\":11.83,\"temp_max\":13.81,\"pressure\":1005,\"humidity\":90,\"sea_level\":1005,\"grnd_level\":1002},\"visibility\":10000,\"wind\":{\"speed\":6.69,\"deg\":260},\"rain\":{\"1h\":4.13},\"clouds\":{\"all\":75},\"dt\":1727793141,\"sys\":{\"type\":2,\"id\":2075535,\"country\":\"GB\",\"sunrise\":1727762491,\"sunset\":1727804306},\"timezone\":3600,\"id\":2643743,\"name\":\"London\",\"cod\":200}",
            FetchCurrentWeatherResponse::class.java
        )
    }

    fun fetchNextWeathersResponse(): FetchNextWeathersResponse? {
        return gson.fromJson(
            "{\"city\":{\"id\":2643743,\"name\":\"London\",\"coord\":{\"lon\":-0.1257,\"lat\":51.5085},\"country\":\"GB\",\"population\":1000000,\"timezone\":3600},\"cod\":\"200\",\"message\":3.6256738,\"cnt\":5,\"list\":[{\"dt\":1727780400,\"sunrise\":1727762491,\"sunset\":1727804306,\"temp\":{\"day\":12.34,\"min\":11.93,\"max\":13.13,\"night\":13.13,\"eve\":12.47,\"morn\":12.03},\"feels_like\":{\"day\":11.9,\"night\":12.82,\"eve\":12.17,\"morn\":11.53},\"pressure\":1007,\"humidity\":87,\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10d\"}],\"speed\":7.03,\"deg\":278,\"gust\":12.86,\"clouds\":100,\"pop\":1,\"rain\":5.34},{\"dt\":1727866800,\"sunrise\":1727848989,\"sunset\":1727890569,\"temp\":{\"day\":14.76,\"min\":12.65,\"max\":15.71,\"night\":12.69,\"eve\":14.07,\"morn\":13.05},\"feels_like\":{\"day\":14.2,\"night\":11.89,\"eve\":13.44,\"morn\":12.81},\"pressure\":1014,\"humidity\":73,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":6.36,\"deg\":351,\"gust\":12.98,\"clouds\":79,\"pop\":0.8,\"rain\":0.16},{\"dt\":1727953200,\"sunrise\":1727935487,\"sunset\":1727976832,\"temp\":{\"day\":14.92,\"min\":11.18,\"max\":16.3,\"night\":11.54,\"eve\":14.51,\"morn\":11.18},\"feels_like\":{\"day\":13.77,\"night\":10.58,\"eve\":13.43,\"morn\":10.39},\"pressure\":1022,\"humidity\":50,\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"speed\":5.13,\"deg\":48,\"gust\":11.1,\"clouds\":63,\"pop\":0.18},{\"dt\":1728039600,\"sunrise\":1728021985,\"sunset\":1728063096,\"temp\":{\"day\":16.01,\"min\":10.31,\"max\":16.12,\"night\":12.09,\"eve\":14.23,\"morn\":10.72},\"feels_like\":{\"day\":14.95,\"night\":11.21,\"eve\":13.35,\"morn\":9.78},\"pressure\":1021,\"humidity\":49,\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"speed\":3.02,\"deg\":117,\"gust\":6.54,\"clouds\":56,\"pop\":0},{\"dt\":1728126000,\"sunrise\":1728108484,\"sunset\":1728149360,\"temp\":{\"day\":13.93,\"min\":11.26,\"max\":14.37,\"night\":13.72,\"eve\":13.64,\"morn\":11.74},\"feels_like\":{\"day\":13.13,\"night\":12.95,\"eve\":12.81,\"morn\":11.08},\"pressure\":1015,\"humidity\":67,\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"speed\":4.91,\"deg\":178,\"gust\":10.22,\"clouds\":95,\"pop\":0}]}",
            FetchNextWeathersResponse::class.java
        )
    }
}