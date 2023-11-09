package com.gap.weatherapp.data

import com.gap.weatherapp.domain.WeatherRepository
import com.gap.weatherapp.domain.model.WeatherEntity
import com.gap.weatherapp.domain.model.WeatherInfo

class WeatherRepositoryImpl: WeatherRepository {
    override fun getAllWeatherForecast(): List<WeatherInfo> {
        TODO("Not yet implemented")
    }

    override fun getDetailsWeatherForecast(): WeatherEntity {
        TODO("Not yet implemented")
    }
}