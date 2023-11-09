package com.gap.weatherapp.data.network.model

import com.google.gson.annotations.SerializedName

data class WeatherInfoDto(
    val clouds: CloudsDto,
    val dt: Int,
    @SerializedName("dt_txt") val dtTxt: String,
    val main: MainDto,
    val weatherDescription: List<WeatherDescriptionDto>,
    val wind: WindDto
)