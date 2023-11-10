package com.gap.weatherapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.gap.weatherapp.R
import com.gap.weatherapp.databinding.FragmentWeatherDetailsBinding
import com.gap.weatherapp.presentation.viewModels.WeatherDetailsViewModel

class WeatherDetailsFragment : Fragment() {

    private var _binding: FragmentWeatherDetailsBinding? = null
    private val binding: FragmentWeatherDetailsBinding
        get() = _binding ?: throw RuntimeException("WeatherDetailsFragment == null")

    private var id: Int = UNDEFINED_ID

    private val viewModel: WeatherDetailsViewModel by lazy {
        ViewModelProvider(this)[WeatherDetailsViewModel::class.java]
    }

    private var lat = COORDINATES_EMPTY
    private var lon = COORDINATES_EMPTY
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = requireArguments().getInt(ID)
        lat = requireArguments().getDouble(COORDINATES_LAT)
        lon = requireArguments().getDouble(COORDINATES_LON)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        workWithViewModel()
        workWithUI()
    }

    private fun workWithUI() {
        val constraintLayout =
            requireActivity().findViewById<ConstraintLayout>(R.id.bottomNavigationView)
        constraintLayout.visibility = View.GONE
        val activity = requireActivity()
        if (activity is AppCompatActivity) {
            activity.setSupportActionBar(binding.mainToolbarCommunication)
            activity.supportActionBar?.title = ""
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            activity.supportActionBar?.setHomeAsUpIndicator(R.drawable.arrow)
        }
        binding.mainToolbarCommunication.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }


    }
    private fun workWithViewModel() {
        viewModel.loadData(lat, lon, id)
        viewModel.cityLD.observe(viewLifecycleOwner) {
            with(binding) {
                toolbarCity.text = it
            }
        }
        viewModel.weatherLD.observe(viewLifecycleOwner) {
            with(binding) {
                tvDate.text = it.dt_txt.substring(0, 11)
                tvClouds.text = it.clouds.all.toString()
                tvHumidity.text = it.main.humidity.toString()
                tvPressure.text = it.main.pressure.toString()
                tvWindSpeed.text = it.wind.speed.toString()
                tvMinTemperature.text = it.main.temp_min.toString()
                tvMaxTemperature.text = it.main.temp_max.toString()
                tvFeelsTemperature.text = it.main.feels_like.toString()
                tvNowTemperature.text = it.main.temp.toString()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        private const val ID = "id"
        private const val UNDEFINED_ID = -1
        private const val COORDINATES_LAT = "coordinates_lat"
        private const val COORDINATES_LON = "coordinates_lon"
        private const val COORDINATES_EMPTY = 0.0
        fun newInstance(id: Int, lat: Double, lon: Double): Fragment {
            return WeatherDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ID, id)
                    putDouble(COORDINATES_LAT, lat)
                    putDouble(COORDINATES_LON, lon)
                }
            }
        }
    }
}