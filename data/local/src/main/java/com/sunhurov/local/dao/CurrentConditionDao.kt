package com.sunhurov.local.dao

import androidx.room.*
import com.sunhurov.model.CurrentCondition
import com.sunhurov.model.Location
import java.util.*

@Dao
abstract class CurrentConditionDao: BaseDao<CurrentCondition>() {

    @Query("SELECT * FROM CurrentCondition WHERE `key` =:locationKey")
    abstract suspend fun loadByLocationKey(locationKey: String): CurrentCondition


    @Query( "DELETE FROM CurrentCondition WHERE `key` =:locationKey ")
    abstract suspend fun deleteByLocationKey(locationKey: String)


    /**
     * Each time we save an current condition details, we update its 'lastRefreshed' field
     * This allows us to know when we have to refresh its data
     * Also save location key for current condition
     */

    suspend fun save(condition: CurrentCondition, locationKey:String) {
        insert(condition.apply {
            lastRefreshed = Date()
            key = locationKey
        })
    }

    suspend fun save(movies: List<CurrentCondition>, locationKey:String) {
        insert(movies.apply { forEach {
            it.lastRefreshed = Date()
            it.key = locationKey
        } })
    }


}