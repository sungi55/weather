package com.sunhurov.home.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sunhurov.home.HomeViewModel
import com.sunhurov.home.databinding.ItemHomeBinding
import com.sunhurov.model.Location

class HomeViewHolder(parent: View): RecyclerView.ViewHolder(parent) {

    private val binding = ItemHomeBinding.bind(parent)

    fun bindTo(location: Location, viewModel: HomeViewModel) {
        binding.location = location
        binding.viewmodel = viewModel
    }
}