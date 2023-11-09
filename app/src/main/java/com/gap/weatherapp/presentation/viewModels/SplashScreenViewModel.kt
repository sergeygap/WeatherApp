package com.gap.weatherapp.presentation.viewModels

import android.app.Application
import android.location.Location
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

class SplashScreenViewModel(application: Application) : AndroidViewModel(application) {

    val geoService by lazy {
        LocationServices.getFusedLocationProviderClient(application)
    }
    val locationRequest by lazy {
        initLocationRequest()
    }

    private lateinit var mLocation: Location

    private fun initLocationRequest(): LocationRequest {
        return LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000)
            .setIntervalMillis(INTERVAL_MILLIS)
            .setMinUpdateIntervalMillis(INTERVAL_MILLIS / 2)
            .setWaitForAccurateLocation(false)
            .setMaxUpdateDelayMillis(10000)
            .build()
    }

    val geoCallback = object : LocationCallback() {
        override fun onLocationResult(geo: LocationResult) {
            Log.d("LocationsTest", "onLocationResult: ${geo.locations.size}")
            for (location in geo.locations) {
                mLocation = location
                Log.d(
                    "LocationsTest",
                    "onLocationResult: lat: ${location.latitude}, lon: ${location.longitude}"
                )
            }
        }
    }

    companion object {
        private const val INTERVAL_MILLIS = 10000L
        const val ACCESS_LOCATION_RC = 100
    }

}