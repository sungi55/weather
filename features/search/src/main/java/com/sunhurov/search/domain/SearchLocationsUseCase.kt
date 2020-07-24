package com.sunhurov.search.domain

import androidx.lifecycle.LiveData
import com.sunhurov.model.Location
import com.sunhurov.repository.LocationRepository
import com.sunhurov.repository.utils.Resource

/**
 * Use case that gets a [Resource][List][Location] from [LocationRepository]
 */
class SearchLocationsUseCase(private val repository: LocationRepository) {

    suspend operator fun invoke( keyword: String): LiveData<Resource<List<Location>>> {
        return repository.searchLocationsByKeyword(keyword)
    }

}