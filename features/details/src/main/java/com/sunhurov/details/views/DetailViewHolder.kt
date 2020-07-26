package com.sunhurov.details.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sunhurov.detail.databinding.ItemHourlyForecastBinding
import com.sunhurov.details.DetailViewModel
import com.sunhurov.model.HourlyForecast

class DetailViewHolder(parent: View): RecyclerView.ViewHolder(parent) {

    private val binding = ItemHourlyForecastBinding.bind(parent)

    fun bindTo(forecast: HourlyForecast, viewModel: DetailViewModel) {
        binding.forecast = forecast
        binding.viewmodel = viewModel
    }
}