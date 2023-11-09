package com.gap.weatherapp.data.network.model

import com.google.gson.annotations.SerializedName

data class MainDto(
   @SerializedName("feels_like") val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
   @SerializedName("temp_max") val tempMax: Double,
   @SerializedName("temp_min") val tempMin: Double
)