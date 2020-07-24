package com.sunhurov.search.views

import androidx.recyclerview.widget.DiffUtil
import com.sunhurov.model.Location

class SearchItemDiffCallback(private val oldList: List<Location>,
                             private val newList: List<Location>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int)
            = oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition].key == newList[newItemPosition].key
    }
}