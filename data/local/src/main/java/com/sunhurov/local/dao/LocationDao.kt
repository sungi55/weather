package com.sunhurov.local.dao

import androidx.room.*
import com.sunhurov.model.Location

@Dao
abstract class LocationDao {

    @Query("SELECT * FROM Location")
    abstract suspend fun loadAll(): List<Location>

    @Delete
    abstract suspend fun delete(location: Location)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(location: Location)

}