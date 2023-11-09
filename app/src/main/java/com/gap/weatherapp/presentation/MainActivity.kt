package com.gap.weatherapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gap.weatherapp.R
import com.gap.weatherapp.databinding.ActivityMainBinding
import com.gap.weatherapp.presentation.notUsebleFragment.FavouritesFragment
import com.gap.weatherapp.presentation.notUsebleFragment.MapFragment
import com.gap.weatherapp.presentation.notUsebleFragment.NewsFragment
import com.gap.weatherapp.presentation.notUsebleFragment.SettingsFragment

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var lat: Double = 0.0
    private var lon: Double = 0.0

    private var savedInstanceState: Bundle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        this.savedInstanceState = savedInstanceState
        weatherCoordinates()
        bottomNavigationMenu()
    }

    private fun weatherCoordinates() {
        if (!intent.hasExtra(COORDINATES_LAT) && !intent.hasExtra(COORDINATES_LON)) {
            finish()
        }
        lat = intent.getDoubleExtra(COORDINATES_LAT, 0.0)
        lon = intent.getDoubleExtra(COORDINATES_LON, 0.0)

        if (savedInstanceState == null) {
            launchFragment(WeatherFragment.newInstance(lat, lon))
        }

    }


    private fun bottomNavigationMenu() {
        with(binding) {
            actionsNews.setOnClickListener {
                actionsSettings.setImageResource(R.drawable.button_settings)
                actionsFavorites.setImageResource(R.drawable.button_favorites)
                actionsNews.setImageResource(R.drawable.button_news_selected)
                actionsMap.setImageResource(R.drawable.button_map)
                actionsWeatherForecast.setImageResource(R.drawable.button_weather)
                launchFragment(NewsFragment.newInstance())
            }
            actionsWeatherForecast.setOnClickListener {
                actionsSettings.setImageResource(R.drawable.button_settings)
                actionsFavorites.setImageResource(R.drawable.button_favorites)
                actionsNews.setImageResource(R.drawable.button_news)
                actionsMap.setImageResource(R.drawable.button_map)
                actionsWeatherForecast.setImageResource(R.drawable.button_weather_selected)
                launchFragment(WeatherFragment.newInstance(lat, lon))
            }

            actionsFavorites.setOnClickListener {
                actionsSettings.setImageResource(R.drawable.button_settings)
                actionsFavorites.setImageResource(R.drawable.button_favorites_selected)
                actionsNews.setImageResource(R.drawable.button_news)
                actionsMap.setImageResource(R.drawable.button_map)
                actionsWeatherForecast.setImageResource(R.drawable.button_weather)
                launchFragment(FavouritesFragment.newInstance())
            }
            actionsMap.setOnClickListener {
                actionsSettings.setImageResource(R.drawable.button_settings)
                actionsFavorites.setImageResource(R.drawable.button_favorites)
                actionsNews.setImageResource(R.drawable.button_news)
                actionsMap.setImageResource(R.drawable.button_map_selected)
                actionsWeatherForecast.setImageResource(R.drawable.button_weather)
                launchFragment(MapFragment.newInstance())
            }
            actionsSettings.setOnClickListener {
                actionsSettings.setImageResource(R.drawable.button_settings_selected)
                actionsFavorites.setImageResource(R.drawable.button_favorites)
                actionsNews.setImageResource(R.drawable.button_news)
                actionsMap.setImageResource(R.drawable.button_map)
                actionsWeatherForecast.setImageResource(R.drawable.button_weather)
                launchFragment(SettingsFragment.newInstance())
            }
        }
    }

    private fun launchFragment(fragment: Fragment) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .commit()
        }
    }

    companion object {
        private const val COORDINATES_LAT = "coordinates_lat"
        private const val COORDINATES_LON = "coordinates_lon"
        fun newIntent(context: Context, lat: Double, lon: Double): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra(COORDINATES_LAT, lat)
                putExtra(COORDINATES_LON, lon)
            }
        }
    }
}
