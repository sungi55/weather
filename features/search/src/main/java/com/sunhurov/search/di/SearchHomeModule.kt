package com.sunhurov.search.di

import com.sunhurov.search.SearchViewModel
import com.sunhurov.search.domain.SaveLocationUseCase
import com.sunhurov.search.domain.SearchLocationsUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureSearchModule = module {
    factory { SearchLocationsUseCase(get()) }
    factory { SaveLocationUseCase(get()) }
    viewModel { SearchViewModel(get(), get(), get()) }
}