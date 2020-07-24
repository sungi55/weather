package com.sunhurov.weather.di

import com.sunhurov.common.BuildConfig
import com.sunhurov.home.di.featureHomeModule
import com.sunhurov.local.di.localModule
import com.sunhurov.remote.di.createRemoteModule
import com.sunhurov.repository.di.repositoryModule
import com.sunhurov.search.di.featureSearchModule

val appComponent = listOf(
    createRemoteModule(BuildConfig.BASE_URL),
    repositoryModule,
    localModule,
    featureHomeModule,
    featureSearchModule
)