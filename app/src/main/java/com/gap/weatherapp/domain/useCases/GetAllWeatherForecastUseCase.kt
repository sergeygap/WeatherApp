package com.gap.weatherapp.domain.useCases

import com.gap.weatherapp.domain.WeatherRepository
import com.gap.weatherapp.domain.model.WeatherInfo

class GetAllWeatherForecastUseCase(
    private val repository: WeatherRepository
) {
    operator fun invoke(): List<WeatherInfo> {
       return repository.getAllWeatherForecast()
    }
}