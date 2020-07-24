package com.sunhurov.home.views

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sunhurov.model.Location
import com.sunhurov.repository.utils.Resource

object HomeBinding {

    @BindingAdapter("app:showWhenLoading")
    @JvmStatic
    fun <T>showWhenLoading(view: SwipeRefreshLayout, resource: Resource<T>?) {
        Log.d(HomeBinding::class.java.simpleName, "Resource: $resource")
        if (resource != null) view.isRefreshing = resource.status == Resource.Status.LOADING
    }

    @BindingAdapter("app:items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, resource: Resource<List<Location>>?) {
        with(recyclerView.adapter as HomeAdapter) {
            resource?.data?.let { updateData(it) }
        }
    }


    @BindingAdapter("app:showWhenEmptyList")
    @JvmStatic
    fun showMessageErrorWhenEmptyList(view: View, resource: Resource<List<Location>>?) {
        if (resource!=null) {
            view.visibility = if (
                (resource.status == Resource.Status.ERROR
                        || resource.status == Resource.Status.SUCCESS)
                && resource.data != null
                && resource.data!!.isEmpty()) View.VISIBLE else View.GONE
        }
    }
}