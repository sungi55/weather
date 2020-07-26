package com.sunhurov.details.views

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sunhurov.common.utils.DateUtils
import com.sunhurov.detail.R
import com.sunhurov.model.CurrentCondition
import com.sunhurov.model.HourlyForecast
import com.sunhurov.repository.utils.Resource


object DetailBinding {


    @BindingAdapter("app:showWhenLoading")
    @JvmStatic
    fun showWhenLoading(view: SwipeRefreshLayout, resource: Resource<List<HourlyForecast>>?) {
        if (resource != null) view.isRefreshing = resource.status == Resource.Status.LOADING
    }

    @BindingAdapter("app:items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, resource: Resource<List<HourlyForecast>>?) {
        with(recyclerView.adapter as DetailAdapter) {
            resource?.data?.let { updateData(it) }
        }
    }


    @BindingAdapter(value = ["color"], requireAll = true)
    @JvmStatic
    fun showFontColor(view: TextView, resource: CurrentCondition?) {
        resource?.temperature?.metric?.value?.let {
            val  textColor: Int  =  when {
                it <= -10.0 -> R.color.colorBlue600
                it in -10.0..20.0 -> R.color.colorPrimaryText
                it >= 20.0 -> R.color.colorRed600
                else -> R.color.colorTextWhite
            }
            view.setTextColor(ContextCompat.getColor(view.context, textColor));
        }
    }

    @BindingAdapter("app:weatherIcon")
    @JvmStatic
    fun showWeatherIcon(view: ImageView, resource: CurrentCondition?) {
        if (resource!=null) {
            resource.weatherIcon?.let {
                val iconRes: Int = getWeatherIcon(it)
                view.setBackgroundResource(iconRes)
            }
        }
    }

    private fun getWeatherIcon(it: Int): Int {
        return when (it) {
            in 1..5 -> R.drawable.ic_sunny
            in 6..11 -> R.drawable.ic_cloudy
            in 12..19 -> R.drawable.ic_rain_sunny
            in 20..23 -> R.drawable.ic_cloudy
            24 -> R.drawable.ic_snowflake
            in 25..29 -> R.drawable.ic_snow_with_rain
            30 -> R.drawable.ic_thermometer_hot
            31 -> R.drawable.ic_thermometer_cold
            32 -> R.drawable.ic_wind
            in 33..35 -> R.drawable.ic_new_moon
            in 36..38 -> R.drawable.ic_night_cloudy
            in 38..44 -> R.drawable.ic_night_rain
            else -> R.drawable.ic_full_moon
        }
    }

    @BindingAdapter("app:weatherIcon")
    @JvmStatic
    fun showWeatherIcon(view: ImageView, resource: Int?) {
        resource?.let {
            val iconRes: Int = getWeatherIcon(it)
            view.setBackgroundResource(iconRes)
        }

    }

    @BindingAdapter("app:showWhenEmptyList")
    @JvmStatic
    fun showMessageErrorWhenEmptyList(view: View, resource: Resource<List<HourlyForecast>>?) {
        if (resource!=null) {
            view.visibility = if (
                (resource.status == Resource.Status.ERROR
                        || resource.status == Resource.Status.SUCCESS)
                && resource.data != null
                && resource.data!!.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    @BindingAdapter("app:dateToString")
    @JvmStatic fun dateToString(view: TextView, value: String) {
        val date = DateUtils.getLocalStringFormat(value)
        view.text = date
    }

    @BindingAdapter(value = ["temperatureFormatText", "temperatureModel"], requireAll = true)
    @JvmStatic
    fun showConditionTemperature(view: TextView, formatText:String?,model: CurrentCondition?) {
         if (model!=null && formatText != null ) {
            val value =  model.temperature?.metric?.value
            val format =  model.temperature?.metric?.unit
            if(value != null && format != null) {
                view.text = String.format(formatText, value, format)
            }
        }
    }

    @BindingAdapter(value = ["temperatureFormatText", "temperatureModel"], requireAll = true)
    @JvmStatic
    fun showHourlyForecastTemperature(view: TextView, formatText:String?,model: HourlyForecast?) {
        if (model!=null && formatText != null ) {
            val value =  model.temperature?.value
            val format =  model.temperature?.unit
            if(value != null && format != null) {
                view.text = String.format(formatText, value, format)
            }
        }
    }

    @BindingAdapter(value = ["humidityFormatText", "humidityModel"], requireAll = true)
    @JvmStatic
    fun showConditionHumidity(view: TextView, formatText:String?,model: CurrentCondition?) {
        if (model!=null && formatText != null ) {
             model.relativeHumidity?.let {
                 view.text = String.format(formatText, it)
             }

        }
    }

    @BindingAdapter(value = ["windFormatText", "windModel"], requireAll = true)
    @JvmStatic
    fun showConditionWind(view: TextView, formatText:String?, model: CurrentCondition?) {
        if (model!=null && formatText != null ) {
            val direction =  model.wind?.direction?.english
            val speed =  model.wind?.speed?.metric?.value
            val unitType =  model.wind?.speed?.metric?.unit
            if(direction != null && speed != null && unitType != null) {
                view.text = String.format(formatText, direction, speed, unitType)
            }

        }
    }

    @BindingAdapter("app:showConditionWeatherText")
    @JvmStatic
    fun showConditionWeatherText(view: TextView, resource: CurrentCondition?) {
        resource?.weatherText?.let {
            view.text = it
        }
    }


}