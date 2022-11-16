package com.example.wheatherapp.adapters

import android.media.browse.MediaBrowser.ItemCallback
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wheatherapp.R
import com.example.wheatherapp.databinding.ListItemBinding

class WeatherAdapter : ListAdapter<DayItem, WeatherAdapter.Holder>(Comparator()) {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ListItemBinding.bind(view)
        fun bind(item: DayItem) = with(binding) {
            tvDate.text = item.time
            tvCondition1.text = item.condition
            tvTemp.text = item.currentTemp
        }
    }

    class Comparator : DiffUtil.ItemCallback<DayItem>() {
        override fun areItemsTheSame(oldItem: DayItem, newItem: DayItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DayItem, newItem: DayItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

}