package com.sunhurov.repository.forecast

import androidx.lifecycle.LiveData
import com.sunhurov.local.dao.CurrentConditionDao
import com.sunhurov.local.dao.HourlyForecastDao
import com.sunhurov.model.CurrentCondition
import com.sunhurov.model.HourlyForecast
import com.sunhurov.remote.WeatherDatasource
import com.sunhurov.repository.BuildConfig
import com.sunhurov.repository.utils.NetworkBoundResource
import com.sunhurov.repository.utils.Resource
import kotlinx.coroutines.Deferred

class ForecastRepositoryImpl(
    private val weatherDatasource: WeatherDatasource,
    private val currentCondition: CurrentConditionDao,
    private val hourlyForecastDao: HourlyForecastDao
): ForecastRepository {


    override suspend fun getCurrentCondition(
        locationKey: String,
        forceRefresh: Boolean
    ): LiveData<Resource<CurrentCondition>> {
        return object : NetworkBoundResource<CurrentCondition, List<CurrentCondition>>() {

            override fun processResponse(response:List<CurrentCondition>): CurrentCondition
                    = response.first()

            override suspend fun saveCallResults(items: CurrentCondition){
                currentCondition.deleteByLocationKey(locationKey)
                currentCondition.save(items, locationKey)
            }


            override fun shouldFetch(data: CurrentCondition?): Boolean
                    = data == null
                    || data.haveToRefreshFromNetwork()
                    || forceRefresh

            override suspend fun loadFromDb(): CurrentCondition
                    = currentCondition.loadByLocationKey(locationKey)

            override fun createCallAsync(): Deferred<List<CurrentCondition>> {
                return  weatherDatasource.fetchCurrentConditionAsync(
                    apiKey = BuildConfig.API_KEY,
                    locationKey = locationKey
                )
            }

        }.build().asLiveData()
    }


    override suspend fun getHourlyForecast(
        locationKey: String,
        forceRefresh: Boolean
    ): LiveData<Resource<List<HourlyForecast>>> {
        return object : NetworkBoundResource<List<HourlyForecast>, List<HourlyForecast>>() {

            override fun processResponse(response:List<HourlyForecast>): List<HourlyForecast>
                    = response

            override suspend fun saveCallResults(items: List<HourlyForecast>){
                hourlyForecastDao.deleteByLocationKey(locationKey)
                hourlyForecastDao.save(items,  locationKey)
            }

            override fun shouldFetch(data: List<HourlyForecast>?): Boolean
                    = data == null
                    || data.isEmpty()
                    || data.last().haveToRefreshFromNetwork()
                    || forceRefresh

            override suspend fun loadFromDb(): List<HourlyForecast>
                    = hourlyForecastDao.loadHourlyForecast(locationKey)

            override fun createCallAsync(): Deferred<List<HourlyForecast>> {
                return  weatherDatasource.fetchHourlyForecastAsync(
                    apiKey = BuildConfig.API_KEY,
                    locationKey = locationKey
                )
            }

        }.build().asLiveData()
    }

}