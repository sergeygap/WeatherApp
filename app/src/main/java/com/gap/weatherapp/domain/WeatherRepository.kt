package com.gap.weatherapp.domain

import com.gap.weatherapp.domain.model.WeatherDto
import com.gap.weatherapp.domain.model.WeatherEntity
import com.gap.weatherapp.domain.model.WeatherInfo

interface WeatherRepository {
   suspend fun getAllWeatherForecast(lat: Double, lon: Double): List<WeatherInfo>
   suspend fun getDetailsWeatherForecast(): WeatherEntity
}