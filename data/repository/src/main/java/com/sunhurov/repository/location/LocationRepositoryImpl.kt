package com.sunhurov.repository.location

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sunhurov.local.dao.LocationDao
import com.sunhurov.model.Location
import com.sunhurov.remote.WeatherDatasource
import com.sunhurov.repository.BuildConfig
import com.sunhurov.repository.utils.Resource
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.coroutineContext

class LocationRepositoryImpl(
    private val dao: LocationDao,
    private val datasource:WeatherDatasource
): LocationRepository {

    override suspend fun getLocationsWithCache(): LiveData<Resource<List<Location>>> {
        val result = MutableLiveData<Resource<List<Location>>>()
        val supervisorJob = SupervisorJob()

        withContext(Dispatchers.Main) { result.value =
            Resource.loading(null)
        }
        CoroutineScope(coroutineContext).launch(supervisorJob) {
            try {
                val locations = dao.loadAll()
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
                    keyword = keyword,
                    apiKey = BuildConfig.API_KEY
                ).await()
                result.postValue(Resource.success(locations))
            } catch (e: Exception) {
                Log.e("NetworkBoundResource", "An error happened: $e")
                result.postValue(Resource.error(e, emptyList()))
            }
        }
        return result

    }

    override suspend fun deleteLocation(location: Location): LiveData<Resource<List<Location>>> {
        val result = MutableLiveData<Resource<List<Location>>>()
        val supervisorJob = SupervisorJob()

        withContext(Dispatchers.Main) { result.value =
            Resource.loading(null)
        }
        CoroutineScope(coroutineContext).launch(supervisorJob) {
            try {
                dao.delete(location)
                val locations = dao.loadAll()
                result.postValue(Resource.success(locations))
            } catch (e: Exception) {
                result.postValue(Resource.error(e, emptyList()))
            }
        }
        return result
    }

    override suspend fun saveLocation(location: Location) {
        dao.insert(location)
    }
}