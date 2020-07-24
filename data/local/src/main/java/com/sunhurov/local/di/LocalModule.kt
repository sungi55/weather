package com.sunhurov.local.di

import com.sunhurov.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val localModule = module {
    single { AppDatabase.buildDatabase(androidContext()) }
    factory { get<AppDatabase>().locationDao()}
    factory { get<AppDatabase>().currentConditionDao()}
    factory { get<AppDatabase>().hourlyForecastDao()}
    factory { get<AppDatabase>().dailyForecastDao()}
}