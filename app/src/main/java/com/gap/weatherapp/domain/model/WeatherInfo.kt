package com.gap.weatherapp.domain.model

import com.gap.weatherapp.data.network.model.CloudsDto
import com.gap.weatherapp.data.network.model.MainDto
import com.gap.weatherapp.data.network.model.WeatherDescriptionDto
import com.gap.weatherapp.data.network.model.WindDto

data class WeatherInfo(
    val clouds: CloudsDto,
    val dt: Int,
    val dtTxt: String,
    val main: MainDto,
    val weatherDescription: List<WeatherDescriptionDto>,
    val wind: WindDto
)