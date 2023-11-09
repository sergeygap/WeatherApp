package com.gap.weatherapp.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.gap.weatherapp.R
import com.gap.weatherapp.data.network.ApiFactory
import com.gap.weatherapp.databinding.ActivityMainBinding
import com.gap.weatherapp.presentation.notUsebleFragment.FavouritesFragment
import com.gap.weatherapp.presentation.notUsebleFragment.MapFragment
import com.gap.weatherapp.presentation.notUsebleFragment.NewsFragment
import com.gap.weatherapp.presentation.notUsebleFragment.SettingsFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var savedInstanceState: Bundle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        this.savedInstanceState = savedInstanceState
        bottomNavigationMenu()

    }


    private fun bottomNavigationMenu() {
        with(binding) {
            actionsNews.setOnClickListener {
                actionsSettings.setImageResource(R.drawable.button_settings)
                actionsFavorites.setImageResource(R.drawable.button_favorites)
                actionsNews.setImageResource(R.drawable.button_news_selected)
                launchFragment(WeatherFragment.newInstance())
            }

            actionsFavorites.setOnClickListener {
                actionsSettings.setImageResource(R.drawable.button_settings)
                actionsFavorites.setImageResource(R.drawable.button_favorites_selected)
                actionsNews.setImageResource(R.drawable.button_news)
                launchFragment(FavouritesFragment.newInstance())
            }
            actionsSettings.setOnClickListener {
                actionsSettings.setImageResource(R.drawable.button_settings_selected)
                actionsFavorites.setImageResource(R.drawable.button_favorites)
                actionsNews.setImageResource(R.drawable.button_news)
                launchFragment(SettingsFragment.newInstance())
            }
            actionsSettings.setOnClickListener {
                actionsSettings.setImageResource(R.drawable.button_settings_selected)
                actionsFavorites.setImageResource(R.drawable.button_favorites)
                actionsNews.setImageResource(R.drawable.button_news)
                launchFragment(NewsFragment.newInstance())
            }
            actionsSettings.setOnClickListener {
                actionsSettings.setImageResource(R.drawable.button_settings_selected)
                actionsFavorites.setImageResource(R.drawable.button_favorites)
                actionsNews.setImageResource(R.drawable.button_news)
                launchFragment(MapFragment.newInstance())
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
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
//        CoroutineScope(Dispatchers.IO).launch {
//            ApiFactory.apiService.getWeather()
//        }