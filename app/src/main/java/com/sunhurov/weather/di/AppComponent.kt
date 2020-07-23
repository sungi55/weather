package com.sunhurov.weather.di

import com.sunhurov.common.BuildConfig
import com.sunhurov.local.di.localModule
import com.sunhurov.remote.di.createRemoteModule
import com.sunhurov.repository.di.repositoryModule

val appComponent = listOf(
    createRemoteModule(BuildConfig.BASE_URL),
    repositoryModule,
    localModule
)