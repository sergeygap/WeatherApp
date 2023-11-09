package com.gap.weatherapp.domain.model

data class WeatherDto(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherInfo>
)