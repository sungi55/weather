package com.sunhurov.home.domain

import androidx.lifecycle.LiveData
import com.sunhurov.model.Location
import com.sunhurov.repository.LocationRepository
import com.sunhurov.repository.utils.Resource

/**
 * Use case that gets a [Resource][List][Location] from [LocationRepository]
 * and makes some specific logic actions on it.
 */
class GetLocationsUseCase(private val repository: LocationRepository) {

    suspend operator fun invoke(): LiveData<Resource<List<Location>>> {
        return repository.getLocationsWithCache()
    }

}