package com.gap.weatherapp.domain.model


data class WeatherEntity(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherInfo>
)