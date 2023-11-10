package com.gap.weatherapp.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.gap.weatherapp.databinding.ItemWeatherBinding
import com.gap.weatherapp.domain.model.WeatherInfo

class WeatherAdapter(
) : ListAdapter<WeatherInfo, WeatherViewHolder>(NewsDiffCallback) {

    var onNewsClickListener: OnNewsClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemWeatherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weatherForecast = getItem(position)
        setUpItem(holder, weatherForecast)
    }


    private fun setUpItem(
        holder: WeatherViewHolder,
        weatherForecast: WeatherInfo
    ) {
        Log.d("testRV", "setUpItem: $weatherForecast")
        with(holder.binding) {
            with(weatherForecast) {
                tvDate.text = dt_txt.substring(0, 11)
                tvClouds.text = clouds.all.toString()
                tvHumidity.text = main.humidity.toString()
                tvPressure.text = main.pressure.toString()
                tvWindSpeed.text = wind.speed.toString()
                tvMinTemperature.text = main.temp_min.toString()
                tvMaxTemperature.text = main.temp_max.toString()
                root.setOnClickListener {
                    onNewsClickListener?.let {
                        it.onNewsClick(dt)
                    }
                }
            }
        }
    }

    interface OnNewsClickListener {
        fun onNewsClick(id: Int)
    }


}