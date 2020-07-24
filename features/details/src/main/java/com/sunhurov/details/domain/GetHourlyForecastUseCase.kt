package com.sunhurov.details.domain

import androidx.lifecycle.LiveData
import com.sunhurov.model.CurrentCondition
import com.sunhurov.model.DailyForecast
import com.sunhurov.model.HourlyForecast
import com.sunhurov.repository.forecast.ForecastRepository
import com.sunhurov.repository.utils.Resource

/**
 * Use case that gets a [Resource] [HourlyForecast] from [ForecastRepository]
 * and makes some specific logic actions on it.
 *
 */
class GetHourlyForecastUseCase(private val repository: ForecastRepository) {

    suspend operator fun invoke(
        key: String,
        forceRefresh:Boolean = false
    ): LiveData<Resource<List<HourlyForecast>>> {
        return repository.getHourlyForecast(locationKey = key, forceRefresh = forceRefresh )
    }
}