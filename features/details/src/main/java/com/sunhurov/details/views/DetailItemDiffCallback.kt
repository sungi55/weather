package com.sunhurov.details.views

import androidx.recyclerview.widget.DiffUtil
import com.sunhurov.model.HourlyForecast
import com.sunhurov.model.Location

class DetailItemDiffCallback(
    private val oldList: List<HourlyForecast>,
    private val newList: List<HourlyForecast>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int)
            = oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition].key == newList[newItemPosition].key
                && oldList[oldItemPosition].epochDateTime == newList[newItemPosition].epochDateTime
                && oldList[oldItemPosition].temperature == newList[newItemPosition].temperature
                && oldList[oldItemPosition].weatherIcon == newList[newItemPosition].weatherIcon

    }
}