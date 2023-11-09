package com.gap.weatherapp.domain.useCases

import com.gap.weatherapp.domain.WeatherRepository
import com.gap.weatherapp.domain.model.WeatherInfo

class GetAllWeatherForecastUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, lon: Double): List<WeatherInfo> {
        return repository.getAllWeatherForecast(lat, lon)
    }
}