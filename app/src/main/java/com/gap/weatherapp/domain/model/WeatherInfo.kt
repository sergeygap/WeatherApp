package com.gap.weatherapp.domain.model

import com.gap.weatherapp.data.network.model.CloudsDto
import com.gap.weatherapp.data.network.model.MainDto
import com.gap.weatherapp.data.network.model.WeatherDescriptionDto
import com.gap.weatherapp.data.network.model.WindDto

data class WeatherInfo(
    val clouds: Clouds,
    val dt: Int,
    val dtTxt: String,
    val main: Main,
    val weatherDescription: List<WeatherDescriptionDto>,
    val wind: Wind
)