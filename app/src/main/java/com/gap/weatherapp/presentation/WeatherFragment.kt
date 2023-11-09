package com.gap.weatherapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gap.weatherapp.R
import com.gap.weatherapp.data.network.ApiFactory
import com.gap.weatherapp.databinding.FragmentWeatherBinding
import com.gap.weatherapp.presentation.adapter.WeatherAdapter
import com.gap.weatherapp.presentation.viewModels.WeatherViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherFragment() : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding: FragmentWeatherBinding
        get() = _binding ?: throw RuntimeException("WeatherFragment == null")
    private val adapter by lazy {
        WeatherAdapter()
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[WeatherViewModel::class.java]
    }

    private var lat = COORDINATES_EMPTY
    private var lon = COORDINATES_EMPTY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lat = requireArguments().getDouble(COORDINATES_LAT)
        lon = requireArguments().getDouble(COORDINATES_LON)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        workWithAdapter()
        workWithViewModel()
    }

    private fun workWithViewModel() {
        viewModel.getWeatherList(lat, lon)
        viewModel.weatherLD.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun workWithAdapter() {
        binding.mainRecyclerView.adapter = adapter
        adapter.onNewsClickListener = object : WeatherAdapter.OnNewsClickListener {
            override fun onNewsClick(id: Int) {
                launchNewsDetailsFragment(id)
            }
        }

    }

    private fun launchNewsDetailsFragment(id: Int) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, WeatherDetailsFragment.newInstance(id))
            .addToBackStack(null).commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val COORDINATES_LAT = "coordinates_lat"
        private const val COORDINATES_LON = "coordinates_lon"
        private const val COORDINATES_EMPTY = 0.0
        fun newInstance(lat: Double, lon: Double): Fragment = WeatherFragment().apply {
            arguments = Bundle().apply {
                putDouble(COORDINATES_LAT, lat)
                putDouble(COORDINATES_LON, lon)
            }
        }
    }

}