package com.gap.weatherapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gap.weatherapp.domain.model.WeatherInfo

object NewsDiffCallback: DiffUtil.ItemCallback<WeatherInfo>() {
    override fun areItemsTheSame(oldItem: WeatherInfo, newItem: WeatherInfo): Boolean {
        return oldItem.dt == newItem.dt
    }

    override fun areContentsTheSame(oldItem: WeatherInfo, newItem: WeatherInfo): Boolean {
        return oldItem == newItem
    }
}