package com.sunhurov.local.dao

import androidx.room.*
import com.sunhurov.model.CurrentCondition
import com.sunhurov.model.DailyForecast
import com.sunhurov.model.Location
import java.util.*

@Dao
abstract class DailyForecastDao: BaseDao<DailyForecast>() {

    @Query("SELECT * FROM DailyForecast ORDER BY time LIMIT 5")
    abstract suspend fun loadDailyForecast(): List<DailyForecast>

    /**
     * Each time we save an current condition details, we update its 'lastRefreshed' field
     * This allows us to know when we have to refresh its data
     */

    suspend fun save(movie: DailyForecast) {
        insert(movie.apply { lastRefreshed = Date() })
    }

    suspend fun save(movies: List<DailyForecast>) {
        insert(movies.apply { forEach { it.lastRefreshed = Date() } })
    }


}