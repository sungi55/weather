package com.sunhurov.details.views

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sunhurov.common.BuildConfig
import com.sunhurov.detail.R
import com.sunhurov.repository.utils.Resource

object DetailBinding {


    @BindingAdapter("app:showWhenLoading")
    @JvmStatic
    fun showWhenLoading(view: SwipeRefreshLayout, status: Resource.Status?) {
        Log.d(DetailBinding::class.java.simpleName, "Status: $status")
        status?.let {
            view.isRefreshing = it == Resource.Status.LOADING
        }
    }

    @BindingAdapter("app:fontColor")
    @JvmStatic
    fun showFontColor(view: TextView, temperature: Int?) {
        Log.d(DetailBinding::class.java.simpleName, "Temperature: $temperature")
        temperature?.let {
            val textColor:Int =  when {
                it < -10 -> R.color.colorBlue600
                it in -10..20 -> R.color.colorPrimaryText
                it > 20 -> R.color.colorRed600
                else -> R.color.colorPrimaryText
            }
            view.setTextColor(textColor)
        }
    }

    @BindingAdapter("app:imageUrl")
    @JvmStatic fun loadImage(view: ImageView, url: String?) {
        Glide.with(view.context)
            .load(BuildConfig.IMAGE_URL + url)
            .placeholder(R.drawable.ic_placeholder)
            .apply(RequestOptions.centerCropTransform())
            .into(view)
    }
}