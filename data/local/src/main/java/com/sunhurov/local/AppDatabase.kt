package com.sunhurov.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sunhurov.local.converter.Converters
import com.sunhurov.local.dao.WeatherDao
import com.sunhurov.model.Weather

@Database(entities = [Weather::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    // DAO
    abstract fun weatherDao(): WeatherDao

    companion object {

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "MovieApp.db")
                .build()
    }
}