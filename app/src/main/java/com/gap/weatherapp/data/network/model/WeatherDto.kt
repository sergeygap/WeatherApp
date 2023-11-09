package com.gap.weatherapp.data.network.model



data class WeatherDto(
    val city: CityDto,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherInfoDto>
)