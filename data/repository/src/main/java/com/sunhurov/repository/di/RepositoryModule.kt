package com.sunhurov.repository.di

import com.sunhurov.repository.*
import com.sunhurov.repository.forecast.ForecastRepository
import com.sunhurov.repository.forecast.ForecastRepositoryImpl
import com.sunhurov.repository.location.LocationRepository
import com.sunhurov.repository.location.LocationRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {
    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
    factory<LocationRepository> { LocationRepositoryImpl(get(), get()) }
    factory<ForecastRepository> { ForecastRepositoryImpl(get(), get(), get(), get()) }
}