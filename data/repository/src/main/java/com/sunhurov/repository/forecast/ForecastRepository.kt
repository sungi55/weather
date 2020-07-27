package com.sunhurov.repository.forecast

import androidx.lifecycle.LiveData
import com.sunhurov.model.CurrentCondition
import com.sunhurov.model.HourlyForecast
import com.sunhurov.repository.utils.Resource

/**
 * Created by Yurii Sunhurov on 24.07.2020
 */
interface ForecastRepository {
    suspend fun getCurrentCondition(locationKey:String, forceRefresh: Boolean): LiveData<Resource<CurrentCondition>>
    suspend fun getHourlyForecast(locationKey:String, forceRefresh: Boolean): LiveData<Resource<List<HourlyForecast>>>
}
