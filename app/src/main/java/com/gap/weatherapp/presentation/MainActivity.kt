package com.gap.weatherapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gap.weatherapp.R
import com.gap.weatherapp.data.network.ApiFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            ApiFactory.apiService.getWeather()
        }

    }
}