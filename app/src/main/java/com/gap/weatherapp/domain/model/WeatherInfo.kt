package com.gap.weatherapp.domain.model
data class WeatherInfo(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val weatherDescription: List<WeatherDescription>,
    val wind: Wind
)