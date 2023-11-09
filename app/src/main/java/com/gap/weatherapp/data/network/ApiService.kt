package com.gap.weatherapp.data.network

import com.gap.weatherapp.domain.model.WeatherEntity
import retrofit2.http.GET

interface ApiService {
    @GET("forecast?lat=55.751244&lon=37.618423&units=metric&appid=a18f2c4bb4015c190dfe835f71408a41&lang=ru&cnt=5")
    suspend fun getWeather(): WeatherEntity
}