package com.sunhurov.search.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sunhurov.model.Location
import com.sunhurov.search.R
import com.sunhurov.search.SearchViewModel

class SearchAdapter(private val viewModel: SearchViewModel): RecyclerView.Adapter<SearchViewHolder>() {

    private val locations: MutableList<Location> = mutableListOf()
    private val filteredLocations: MutableList<Location> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false))

    override fun getItemCount(): Int
            = locations.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int)
            = holder.bindTo(locations[position], viewModel)


    fun updateData(items: List<Location>) {
        val diffCallback = SearchItemDiffCallback(locations, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        locations.clear()
        locations.addAll(items)
        filteredLocations.clear()
        filteredLocations.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

}