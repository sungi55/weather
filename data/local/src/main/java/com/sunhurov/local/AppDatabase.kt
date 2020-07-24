package com.sunhurov.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sunhurov.local.converter.Converters
import com.sunhurov.local.dao.LocationDao
import com.sunhurov.model.Location

@Database(entities = [Location::class], version = 8, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    // DAO
    abstract fun locationDao(): LocationDao

    companion object {

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "WeatherApp.db")
                .build()
    }
}