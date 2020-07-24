package com.sunhurov.search.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sunhurov.model.Location
import com.sunhurov.search.SearchViewModel
import com.sunhurov.search.databinding.ItemSearchBinding

class SearchViewHolder(parent: View): RecyclerView.ViewHolder(parent) {

    private val binding = ItemSearchBinding.bind(parent)

    fun bindTo(location: Location, viewModel: SearchViewModel) {
        binding.location = location
        binding.viewmodel = viewModel
    }
}