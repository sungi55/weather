package com.sunhurov.repository.di

import com.sunhurov.repository.*
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

/**
 * Created by Yurii Sunhurov on 13.05.2020
 */

val repositoryModule = module {
    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
    factory<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }
}