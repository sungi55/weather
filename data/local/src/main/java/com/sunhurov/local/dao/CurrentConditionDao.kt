package com.sunhurov.local.dao

import androidx.room.*
import com.sunhurov.model.CurrentCondition
import com.sunhurov.model.Location
import java.util.*

@Dao
abstract class CurrentConditionDao: BaseDao<CurrentCondition>() {

    @Query("SELECT * FROM CurrentCondition ORDER BY lastRefreshed LIMIT 1")
    abstract suspend fun loadCondition(): CurrentCondition

    /**
     * Each time we save an current condition details, we update its 'lastRefreshed' field
     * This allows us to know when we have to refresh its data
     */

    suspend fun save(movie: CurrentCondition) {
        insert(movie.apply { lastRefreshed = Date() })
    }

    suspend fun save(movies: List<CurrentCondition>) {
        insert(movies.apply { forEach { it.lastRefreshed = Date() } })
    }


}