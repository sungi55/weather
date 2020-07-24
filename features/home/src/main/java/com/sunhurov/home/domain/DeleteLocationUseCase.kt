package com.sunhurov.home.domain

import androidx.lifecycle.LiveData
import com.sunhurov.model.Location
import com.sunhurov.repository.LocationRepository
import com.sunhurov.repository.utils.Resource

/**
 * Use case that delete [Location] from [LocationRepository]
 * and makes some specific logic actions on it.
 */
class DeleteLocationUseCase(private val repository: LocationRepository) {

    suspend operator fun invoke(location: Location): LiveData<Resource<List<Location>>> {
        return repository.deleteLocation(location)
    }

}