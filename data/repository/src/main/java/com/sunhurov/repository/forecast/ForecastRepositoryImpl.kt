package com.sunhurov.repository.forecast

import androidx.lifecycle.LiveData
import com.sunhurov.local.dao.CurrentConditionDao
import com.sunhurov.local.dao.DailyForecastDao
import com.sunhurov.local.dao.HourlyForecastDao
import com.sunhurov.model.CurrentCondition
import com.sunhurov.model.DailyForecast
import com.sunhurov.model.HourlyForecast
import com.sunhurov.remote.WeatherDatasource
import com.sunhurov.repository.BuildConfig
import com.sunhurov.repository.utils.NetworkBoundResource
import com.sunhurov.repository.utils.Resource
import kotlinx.coroutines.Deferred

class ForecastRepositoryImpl(
    private val weatherDatasource: WeatherDatasource,
    private val currentCondition: CurrentConditionDao,
    private val hourlyForecastDao: HourlyForecastDao,
    private val dailyForecastDao: DailyForecastDao
): ForecastRepository {


    override suspend fun getCurrentCondition(
        locationKey: String,
        forceRefresh: Boolean
    ): LiveData<Resource<CurrentCondition>> {
        return object : NetworkBoundResource<CurrentCondition, CurrentCondition>() {

            override fun processResponse(response:CurrentCondition): CurrentCondition
                    = response

            override suspend fun saveCallResults(items: CurrentCondition)
                    = currentCondition.save(items)

            override fun shouldFetch(data: CurrentCondition?): Boolean
                    = data == null
                    || data.haveToRefreshFromNetwork()
                    || forceRefresh

            override suspend fun loadFromDb(): CurrentCondition
                    = currentCondition.loadCondition()

            override fun createCallAsync(): Deferred<CurrentCondition> {
                return  weatherDatasource.fetchCurrentCondition(
                    apiKey = BuildConfig.API_KEY,
                    locationKey = locationKey)
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

            override suspend fun saveCallResults(items: List<HourlyForecast>)
                    = hourlyForecastDao.save(items)

            override fun shouldFetch(data: List<HourlyForecast>?): Boolean
                    = data == null
                    || data.isEmpty()
                    || data.last().haveToRefreshFromNetwork()
                    || forceRefresh

            override suspend fun loadFromDb(): List<HourlyForecast>
                    = hourlyForecastDao.loadHourlyForecast()

            override fun createCallAsync(): Deferred<List<HourlyForecast>> {
                return  weatherDatasource.fetchHourlyForecast(
                    apiKey = BuildConfig.API_KEY,
                    locationKey = locationKey)
            }

        }.build().asLiveData()
    }

    override suspend fun getDailyForecast(
        locationKey: String,
        forceRefresh: Boolean
    ): LiveData<Resource<List<DailyForecast>>> {
        return object : NetworkBoundResource<List<DailyForecast>, List<DailyForecast>>() {

            override fun processResponse(response:List<DailyForecast>): List<DailyForecast>
                    = response

            override suspend fun saveCallResults(items: List<DailyForecast>)
                    = dailyForecastDao.save(items)

            override fun shouldFetch(data: List<DailyForecast>?): Boolean
                    = data == null
                    || data.isEmpty()
                    || data.last().haveToRefreshFromNetwork()
                    || forceRefresh

            override suspend fun loadFromDb(): List<DailyForecast>
                    = dailyForecastDao.loadDailyForecast()

            override fun createCallAsync(): Deferred<List<DailyForecast>> {
                return  weatherDatasource.fetchDailyForecast(
                    apiKey = BuildConfig.API_KEY,
                    locationKey = locationKey)
            }

        }.build().asLiveData()
    }

}