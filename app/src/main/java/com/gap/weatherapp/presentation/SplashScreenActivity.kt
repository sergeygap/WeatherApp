package com.gap.weatherapp.presentation

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.gap.weatherapp.databinding.ActivitySplashScreenBinding
import com.gap.weatherapp.presentation.viewModels.SplashScreenViewModel

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private val binding: ActivitySplashScreenBinding by lazy {
        ActivitySplashScreenBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[SplashScreenViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        startActivity()
    }

    private fun startActivity() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        checkPermissionLocation()
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
            SplashScreenViewModel.ACCESS_LOCATION_RC
        )
    }

    @SuppressLint("MissingPermission")
    private fun requestLocation() {
        viewModel.geoService.requestLocationUpdates(
            viewModel.locationRequest,
            viewModel.geoCallback,
            null
        )
        Handler(Looper.getMainLooper()).postDelayed({
            startMainActivity()
            finish()
        }, 1000)
    }


    private fun startMainActivity() {
        val intent = MainActivity.newIntent(this)
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == SplashScreenViewModel.ACCESS_LOCATION_RC && grantResults.isNotEmpty()) {
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


}