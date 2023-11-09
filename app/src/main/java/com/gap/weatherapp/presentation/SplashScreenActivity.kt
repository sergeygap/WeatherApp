package com.gap.weatherapp.presentation

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.gap.weatherapp.R
import com.gap.weatherapp.databinding.ActivitySplashScreenBinding
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private val binding: ActivitySplashScreenBinding by lazy {
        ActivitySplashScreenBinding.inflate(layoutInflater)
    }

    private val geoService by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }
    private val locationRequest by lazy {
        initLocationRequest()
    }

    private lateinit var mLocation: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        startActivity()

    }

    private fun checkPermissionLocation() {
        val permissionGrantedFineLocation = ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val permissionGrantedCoarseLocation = ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        if (permissionGrantedCoarseLocation && permissionGrantedFineLocation) {
            requestLocation()
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            ACCESS_LOCATION_RC
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == ACCESS_LOCATION_RC && grantResults.isNotEmpty()) {
            val permissionGrantedCoarseLocation =
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            val permissionGrantedFineLocation = grantResults[1] == PackageManager.PERMISSION_GRANTED
            if (permissionGrantedCoarseLocation && permissionGrantedFineLocation) {
                requestLocation()
            } else {
                binding.errorTV.visibility = View.VISIBLE
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    @SuppressLint("MissingPermission")
    private fun requestLocation() {
        geoService.requestLocationUpdates(locationRequest, geoCallback, null)
        Handler(Looper.getMainLooper()).postDelayed({
            startMainActivity()
            finish()
        }, 1000)
    }

    private fun startActivity() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        checkPermissionLocation()
    }

    private fun startMainActivity() {
        val intent = MainActivity.newIntent(this)
        startActivity(intent)
    }

    private fun initLocationRequest(): LocationRequest {
        return LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000)
            .setIntervalMillis(INTERVAL_MILLIS)
            .setMinUpdateIntervalMillis(INTERVAL_MILLIS / 2)
            .setWaitForAccurateLocation(false)
            .setMaxUpdateDelayMillis(10000)
            .build()
    }

    private val geoCallback = object : LocationCallback() {
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
        private const val ACCESS_LOCATION_RC = 100
    }

}