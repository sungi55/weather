package com.sunhurov.weather

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.sunhurov.weather.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Yurii Sunhurov on 23.07.2020
 */

open class App: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        configureDi()
    }

    // CONFIGURATION ---
    open fun configureDi() =
        startKoin {
            //inject Android context
            androidContext(this@App)
            // use modules
            modules(provideComponent())
        }


    open fun provideComponent() = appComponent
}