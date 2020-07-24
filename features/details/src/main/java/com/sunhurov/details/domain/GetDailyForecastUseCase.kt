package com.sunhurov.details.domain

import androidx.lifecycle.LiveData
import com.sunhurov.model.CurrentCondition
import com.sunhurov.model.DailyForecast
import com.sunhurov.repository.forecast.ForecastRepository
import com.sunhurov.repository.utils.Resource

/**
 * Use case that gets a [Resource] [DailyForecast] from [ForecastRepository]
 * and makes some specific logic actions on it.
 *
 */
class GetDailyForecastUseCase(private val repository: ForecastRepository) {

    suspend operator fun invoke(
        key: String,
        forceRefresh:Boolean = false
    ): LiveData<Resource<List<DailyForecast>>> {
        return repository.getDailyForecast(locationKey = key, forceRefresh = forceRefresh )
    }
}