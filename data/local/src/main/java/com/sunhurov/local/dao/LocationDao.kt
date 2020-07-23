package com.sunhurov.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.sunhurov.model.Location

@Dao
abstract class LocationDao: BaseDao<Location>() {

    @Query("SELECT * FROM Location")
    abstract suspend fun getLocations(): List<Location>

    suspend fun save(location: Location) {
        insert(location)
    }

    suspend fun save(locations: List<Location>) {
        insert(locations)
    }

}