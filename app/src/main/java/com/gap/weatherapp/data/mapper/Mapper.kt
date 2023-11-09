package com.gap.weatherapp.data.mapper

import com.gap.weatherapp.data.network.model.WeatherDto
import com.gap.weatherapp.domain.model.City
import com.gap.weatherapp.domain.model.Clouds
import com.gap.weatherapp.domain.model.Main
import com.gap.weatherapp.domain.model.WeatherDescription
import com.gap.weatherapp.domain.model.WeatherEntity
import com.gap.weatherapp.domain.model.WeatherInfo
import com.gap.weatherapp.domain.model.Wind

class Mapper {
    fun mapDtoToEntity(dto: WeatherDto): WeatherEntity {
        return WeatherEntity(
            city = City(dto.city.name),
            cnt = dto.cnt,
            cod = dto.cod,
            list = dto.list.map { dto ->
                WeatherInfo(
                    clouds = Clouds(dto.clouds.all),
                    dt = dto.dt,
                    dt_txt = dto.dtTxt,
                    main = Main(
                        dto.main.feelsLike,
                        dto.main.humidity,
                        dto.main.pressure,
                        dto.main.temp,
                        dto.main.tempMax,
                        dto.main.tempMin
                    ),
                    weatherDescription = dto.weatherDescription.map {
                        WeatherDescription(it.description)
                    },
                    wind = Wind(dto.wind.speed)
                )
            }
        )
    }
}