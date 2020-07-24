package com.sunhurov.search.domain

import androidx.lifecycle.LiveData
import com.sunhurov.model.Location
import com.sunhurov.repository.location.LocationRepository
import com.sunhurov.repository.utils.Resource

/**
 * Use case that gets a [Resource][List][Location] from [LocationRepository]
 */
class SaveLocationUseCase(private val repository: LocationRepository) {

    suspend operator fun invoke(location: Location){
         repository.saveLocation(location)
    }

}