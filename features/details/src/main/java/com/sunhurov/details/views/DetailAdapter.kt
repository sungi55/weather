package com.sunhurov.details.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sunhurov.detail.R
import com.sunhurov.details.DetailViewModel
import com.sunhurov.model.HourlyForecast

class DetailAdapter(private val viewModel: DetailViewModel): RecyclerView.Adapter<DetailViewHolder>() {

    private val hourlyForecast: MutableList<HourlyForecast> = mutableListOf()
    private val filteredMovies: MutableList<HourlyForecast> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = DetailViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_hourly_forecast, parent, false))

    override fun getItemCount(): Int
            = hourlyForecast.size

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int)
            = holder.bindTo(hourlyForecast[position], viewModel)


    fun updateData(items: List<HourlyForecast>) {
        val diffCallback = DetailItemDiffCallback(hourlyForecast, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        hourlyForecast.clear()
        hourlyForecast.addAll(items)
        filteredMovies.clear()
        filteredMovies.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

}