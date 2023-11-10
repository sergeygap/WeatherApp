package com.gap.weatherapp.data

import com.gap.weatherapp.data.network.ApiFactory
import com.gap.weatherapp.domain.WeatherRepository
import com.gap.weatherapp.domain.model.WeatherEntity

class WeatherRepositoryImpl : WeatherRepository {

    private val apiService = ApiFactory.apiService

    override suspend fun getAllWeatherForecast(lat: Double, lon: Double): WeatherEntity {
        try {
            return apiService.getAllWeather(lat, lon)
        } catch (e: Exception) {
            throw RuntimeException("User has not internet")
        }
    }


}