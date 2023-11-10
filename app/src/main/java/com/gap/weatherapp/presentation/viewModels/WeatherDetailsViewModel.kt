package com.gap.weatherapp.presentation.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gap.weatherapp.data.WeatherRepositoryImpl
import com.gap.weatherapp.domain.model.WeatherEntity
import com.gap.weatherapp.domain.model.WeatherInfo
import com.gap.weatherapp.domain.useCases.GetAllWeatherForecastUseCase
import kotlinx.coroutines.launch

class WeatherDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = WeatherRepositoryImpl()
    private val getAllWeatherForecastUseCase = GetAllWeatherForecastUseCase(repository)

    private val _weatherLD = MutableLiveData<WeatherInfo>()
    val weatherLD: LiveData<WeatherInfo>
        get() = _weatherLD

    private val _cityLD = MutableLiveData<String>()
    val cityLD: LiveData<String>
        get() = _cityLD

    fun loadData(lat: Double, lon: Double, id: Int) {
        viewModelScope.launch {
            val entity = getAllWeatherForecastUseCase(lat, lon)
            when (id) {
                0 -> {
                    _weatherLD.value = entity.list[0]
                    _cityLD.value = entity.city.name
                }
                1 -> {
                    _weatherLD.value = entity.list[8]
                    _cityLD.value = entity.city.name
                }
                2 -> {
                    _weatherLD.value = entity.list[16]
                    _cityLD.value = entity.city.name
                }
                3 -> {
                    _weatherLD.value = entity.list[24]
                    _cityLD.value = entity.city.name
                }
                4 -> {
                    _weatherLD.value = entity.list[32]
                    _cityLD.value = entity.city.name
                }
                else -> {
                    _weatherLD.value = entity.list[0]
                    _cityLD.value = entity.city.name
                }
            }
        }

    }
}