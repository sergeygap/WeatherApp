package com.example.wheatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wheatherapp.R
import com.example.wheatherapp.adapters.DayItem
import com.example.wheatherapp.adapters.WeatherAdapter
import com.example.wheatherapp.databinding.FragmentHoursBinding
import com.example.wheatherapp.databinding.FragmentMainBinding


class HoursFragment : Fragment() {

    private lateinit var binding: FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHoursBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }


    private fun initRecyclerView() = with(binding) {
        binding.rcView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter()
        rcView.adapter = adapter
        val list = listOf(
            DayItem("", "13:00", "Sunny", "", "34", "", "", ""),
            DayItem("", "14:00", "Sunny", "", "23", "", "", ""),
            DayItem("", "15:00", "Sunny", "", "11", "", "", ""),
            DayItem("", "16:00", "Sunny", "", "15", "", "", "")
        )
        adapter.submitList(list)
    }


    companion object {

        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}