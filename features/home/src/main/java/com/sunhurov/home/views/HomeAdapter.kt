package com.sunhurov.home.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sunhurov.home.HomeViewModel
import com.sunhurov.home.R
import com.sunhurov.model.Location

class HomeAdapter(private val viewModel: HomeViewModel): RecyclerView.Adapter<HomeViewHolder>() {

    private val movies: MutableList<Location> = mutableListOf()
    private val filteredMovies: MutableList<Location> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false))

    override fun getItemCount(): Int
            = movies.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int)
            = holder.bindTo(movies[position], viewModel)


    fun updateData(items: List<Location>) {
        val diffCallback = HomeItemDiffCallback(movies, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        movies.clear()
        movies.addAll(items)
        filteredMovies.clear()
        filteredMovies.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

}