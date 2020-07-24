package com.sunhurov.home.di

import com.sunhurov.home.HomeViewModel
import com.sunhurov.home.domain.DeleteLocationUseCase
import com.sunhurov.home.domain.GetLocationHistoryUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureHomeModule = module {
    factory { GetLocationHistoryUseCase(get()) }
    factory { DeleteLocationUseCase(get()) }
    viewModel { HomeViewModel(get(), get(), get()) }
}