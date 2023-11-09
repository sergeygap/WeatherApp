package com.gap.weatherapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gap.weatherapp.R
import com.gap.weatherapp.databinding.FragmentWeatherDetailsBinding

class WeatherDetailsFragment : Fragment() {

    private var _binding: FragmentWeatherDetailsBinding? = null
    private val binding: FragmentWeatherDetailsBinding
        get() = _binding ?: throw RuntimeException("WeatherDetailsFragment == null")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        private const val ID = "id"
        private const val UNDEFINED_ID = -1
        fun newInstance(id: Int): Fragment {
            return WeatherDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ID, id)
                }
            }
        }
    }
}