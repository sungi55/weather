package com.sunhurov.home.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sunhurov.home.HomeViewModel
import com.sunhurov.home.R
import com.sunhurov.model.Location

class HomeAdapter(private val viewModel: HomeViewModel): RecyclerView.Adapter<HomeViewHolder>() {

    private val locations: MutableList<Location> = mutableListOf()
    private val filteredMovies: MutableList<Location> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false))

    override fun getItemCount(): Int
            = locations.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int)
            = holder.bindTo(locations[position], viewModel)


    fun updateData(items: List<Location>) {
        val diffCallback = HomeItemDiffCallback(locations, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        locations.clear()
        locations.addAll(items)
        filteredMovies.clear()
        filteredMovies.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

}