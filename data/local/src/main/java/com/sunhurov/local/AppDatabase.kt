package com.sunhurov.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sunhurov.local.converter.Converters
import com.sunhurov.local.dao.CurrentConditionDao
import com.sunhurov.local.dao.DailyForecastDao
import com.sunhurov.local.dao.HourlyForecastDao
import com.sunhurov.local.dao.LocationDao
import com.sunhurov.model.CurrentCondition
import com.sunhurov.model.DailyForecast
import com.sunhurov.model.HourlyForecast
import com.sunhurov.model.Location

@Database(entities = [
    Location::class,
    CurrentCondition::class,
    HourlyForecast::class,
    DailyForecast::class
], version = 9, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    // DAO
    abstract fun locationDao(): LocationDao

    abstract fun currentConditionDao(): CurrentConditionDao

    abstract fun hourlyForecastDao(): HourlyForecastDao

    abstract fun dailyForecastDao(): DailyForecastDao

    companion object {

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "WeatherApp.db")
                .build()
    }
}