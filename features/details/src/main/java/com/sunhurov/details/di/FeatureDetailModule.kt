package com.sunhurov.details.di

import com.sunhurov.details.DetailViewModel
import com.sunhurov.details.domain.GetCurrentConditionUseCase
import com.sunhurov.details.domain.GetHourlyForecastUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureDetailModule = module {
    factory { GetCurrentConditionUseCase(get()) }
//    factory { GetDailyForecastUseCase(get()) }
    factory { GetHourlyForecastUseCase(get()) }
    viewModel { DetailViewModel(get(), get(), get()) }
}