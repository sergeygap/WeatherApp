package com.gap.weatherapp.presentation.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gap.weatherapp.data.WeatherRepositoryImpl
import com.gap.weatherapp.domain.model.WeatherInfo
import com.gap.weatherapp.domain.useCases.GetAllWeatherForecastUseCase
import kotlinx.coroutines.launch

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = WeatherRepositoryImpl()
    private val getAllWeatherForecastUseCase = GetAllWeatherForecastUseCase(repository)

    private val _weatherLD = MutableLiveData<List<WeatherInfo>>()
    val weatherLD: LiveData<List<WeatherInfo>>
        get() = _weatherLD

    fun getWeatherList(lat: Double, lon: Double) {
        viewModelScope.launch {
            val entity = getAllWeatherForecastUseCase(lat, lon)
            val list = entity.list
            val newsList = mutableListOf<WeatherInfo>()
            newsList.add(list[0])
            newsList.add(list[8])
            newsList.add(list[16])
            newsList.add(list[24])
            newsList.add(list[32])
            _weatherLD.value = newsList
        }

    }
}