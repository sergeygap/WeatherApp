package com.gap.weatherapp.domain.useCases

import com.gap.weatherapp.domain.WeatherRepository
import com.gap.weatherapp.domain.model.WeatherEntity

class GetDetailsWeatherForecast(
    private val repository: WeatherRepository
) {
    operator fun invoke(): WeatherEntity {
        return repository.getDetailsWeatherForecast()
    }
}