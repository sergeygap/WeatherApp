package com.gap.weatherapp.data.mapper

import com.gap.weatherapp.domain.model.WeatherDto
import com.gap.weatherapp.domain.model.WeatherEntity

class Mapper {
    fun mapDtoToEntity(dto: WeatherDto): WeatherEntity   {
        return WeatherEntity(
            city = dto.city,
            cnt = dto.cnt,
            cod = dto.cod,
            list = dto.list
        )
    }
}