package com.sunhurov.local.dao

import androidx.room.*
import com.sunhurov.model.CurrentCondition
import com.sunhurov.model.HourlyForecast
import com.sunhurov.model.Location
import java.util.*

@Dao
abstract class HourlyForecastDao: BaseDao<HourlyForecast>() {

    @Query("SELECT * FROM HourlyForecast ORDER BY time LIMIT 12")
    abstract suspend fun loadHourlyForecast(): List<HourlyForecast>

    /**
     * Each time we save an current condition details, we update its 'lastRefreshed' field
     * This allows us to know when we have to refresh its data
     */

    suspend fun save(movie: HourlyForecast) {
        insert(movie.apply { lastRefreshed = Date() })
    }

    suspend fun save(movies: List<HourlyForecast>) {
        insert(movies.apply { forEach { it.lastRefreshed = Date() } })
    }


}