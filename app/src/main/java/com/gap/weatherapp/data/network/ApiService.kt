package com.gap.weatherapp.data.network

import com.gap.weatherapp.domain.model.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast?units=metric&appid=a18f2c4bb4015c190dfe835f71408a41&lang=ru")
    suspend fun getAllWeather(
        @Query(LAT) lat: Double,
        @Query(LON) lon: Double
    ): WeatherDto


    companion object {
        private const val LAT = "lat"
        private const val LON = "lon"
    }
}