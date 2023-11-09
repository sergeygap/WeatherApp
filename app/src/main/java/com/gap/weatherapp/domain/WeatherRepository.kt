package com.gap.weatherapp.domain

import com.gap.weatherapp.domain.model.WeatherEntity
import com.gap.weatherapp.domain.model.WeatherInfo

interface WeatherRepository {
    fun getAllWeatherForecast(): List<WeatherInfo>
    fun getDetailsWeatherForecast(): WeatherEntity
}