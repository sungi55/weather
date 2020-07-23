package com.sunhurov.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sunhurov.local.dao.LocationDao
import com.sunhurov.model.Location
import com.sunhurov.remote.LocationDatasource
import com.sunhurov.repository.utils.Resource
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.coroutineContext

interface LocationRepository {
    suspend fun getLocationsWithCache(): LiveData<Resource<List<Location>>>
    suspend fun saveLocation(location: Location)
    suspend fun searchLocationsByKeyword(keyword: String): LiveData<Resource<List<Location>>>
}

class LocationRepositoryImpl(
    private val dao: LocationDao,
    private val datasource:LocationDatasource
): LocationRepository {

    override suspend fun getLocationsWithCache(): LiveData<Resource<List<Location>>> {
        val result = MutableLiveData<Resource<List<Location>>>()
        val supervisorJob = SupervisorJob()

        withContext(Dispatchers.Main) { result.value =
            Resource.loading(null)
        }
        CoroutineScope(coroutineContext).launch(supervisorJob) {
            try {
                val locations = dao.getLocations()
                result.postValue(Resource.success(locations))
            } catch (e: Exception) {
                result.postValue(Resource.error(e, emptyList()))
            }
        }
        return result
    }

    override suspend fun searchLocationsByKeyword(keyword: String): LiveData<Resource<List<Location>>> {

        val result = MutableLiveData<Resource<List<Location>>>()
        val supervisorJob = SupervisorJob()

        withContext(Dispatchers.Main) { result.value =
            Resource.loading(null)
        }
        CoroutineScope(coroutineContext).launch(supervisorJob) {
            try {
                val locations = datasource.fetchLocationsAsync(
                    keyword, BuildConfig.API_KEY).await()
                result.postValue(Resource.success(locations.items))
            } catch (e: Exception) {
                Log.e("NetworkBoundResource", "An error happened: $e")
                result.postValue(Resource.error(e, emptyList()))
            }
        }
        return result

    }

    override suspend fun saveLocation(location: Location) {
        dao.save(location)
    }
}