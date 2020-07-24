package com.sunhurov.details.domain

import androidx.lifecycle.LiveData
import com.sunhurov.model.CurrentCondition
import com.sunhurov.repository.forecast.ForecastRepository
import com.sunhurov.repository.utils.Resource

/**
 * Use case that gets a [Resource] [CurrentCondition] from [ForecastRepository]
 * and makes some specific logic actions on it.
 *
 */
class GetCurrentConditionUseCase(private val repository: ForecastRepository) {

    suspend operator fun invoke(
        key: String,
        forceRefresh:Boolean = false
    ): LiveData<Resource<CurrentCondition>> {
        return repository.getCurrentCondition(locationKey = key, forceRefresh = forceRefresh )
    }
}