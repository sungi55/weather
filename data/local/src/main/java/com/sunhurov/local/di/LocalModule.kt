package com.sunhurov.local.di

import com.sunhurov.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Yurii Sunhurov on 13.05.2020
 */
private const val DATABASE = "DATABASE"

val localModule = module {
    single { AppDatabase.buildDatabase(androidContext()) }
    factory { get<AppDatabase>().weatherDao()}
}