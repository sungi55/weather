package com.sunhurov.home.di

import com.sunhurov.home.HomeViewModel
import com.sunhurov.home.domain.GetLocationsUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureHomeModule = module {
    factory { GetLocationsUseCase(get()) }
    viewModel { HomeViewModel(get(), get()) }
}