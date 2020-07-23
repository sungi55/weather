package com.sunhurov.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.sunhurov.model.Weather
import java.util.*

@Dao
abstract class WeatherDao: BaseDao<Weather>() {

    @Query("SELECT * FROM Weather WHERE id = :id LIMIT 1")
    abstract suspend fun getWeather(id: Int): Weather

    // ---

    /**
     * Each time we save an weather, we update its 'lastRefreshed' field
     * This allows us to know when we have to refresh its data
     */

    suspend fun save(movie: Weather) {
        insert(movie.apply { lastRefreshed = Date() })
    }

    suspend fun save(movies: List<Weather>) {
        insert(movies.apply { forEach { it.lastRefreshed = Date() } })
    }

}