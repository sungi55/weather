package com.sunhurov.local.dao

import androidx.room.*
import com.sunhurov.model.CurrentCondition
import com.sunhurov.model.HourlyForecast
import com.sunhurov.model.Location
import java.util.*

@Dao
abstract class HourlyForecastDao: BaseDao<HourlyForecast>() {

    @Query("SELECT * FROM HourlyForecast WHERE `key` = :key ORDER BY epochDateTime ")
    abstract suspend fun loadHourlyForecast(key:String): List<HourlyForecast>

    @Query( "DELETE FROM HourlyForecast WHERE `key` =:key ")
    abstract suspend fun deleteByLocationKey(key: String)

    /**
     * Each time we save an current condition details, we update its 'lastRefreshed' field
     * This allows us to know when we have to refresh its data
     * Also assign for each forecast location key
     */

    suspend fun save(forecast: HourlyForecast, locationKey:String) {
        insert(forecast.apply {
            lastRefreshed = Date()
            key = locationKey
        })
    }

    suspend fun save(forecasts: List<HourlyForecast>, locationKey: String) {
        insert(forecasts.apply { forEach {
            it.lastRefreshed = Date()
            it.key = locationKey
        } })
    }


}