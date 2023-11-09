package com.gap.weatherapp.data

import com.gap.weatherapp.data.mapper.Mapper
import com.gap.weatherapp.data.network.ApiFactory
import com.gap.weatherapp.domain.WeatherRepository
import com.gap.weatherapp.domain.model.WeatherEntity

class WeatherRepositoryImpl : WeatherRepository {

    private val apiService = ApiFactory.apiService
    private val mapper = Mapper()

    override suspend fun getAllWeatherForecast(lat: Double, lon: Double): WeatherEntity {
        return mapper.mapDtoToEntity(apiService.getAllWeather(lat, lon))
    }

    override suspend fun getDetailsWeatherForecast(): WeatherEntity {
        TODO("Not yet implemented")
    }

}