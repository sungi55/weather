package com.sunhurov.repository.location

import androidx.lifecycle.LiveData
import com.sunhurov.model.Location
import com.sunhurov.repository.utils.Resource

/**
 * Created by Yurii Sunhurov on 24.07.2020
 */
interface LocationRepository {
    suspend fun getLocationsWithCache(): LiveData<Resource<List<Location>>>
    suspend fun saveLocation(location: Location)
    suspend fun searchLocationsByKeyword(keyword: String): LiveData<Resource<List<Location>>>
    suspend fun deleteLocation(location: Location): LiveData<Resource<List<Location>>>
}