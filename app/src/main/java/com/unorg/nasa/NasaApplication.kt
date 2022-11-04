package com.unorg.nasa

import android.app.Application
import com.unorg.nasa.di.NetworkModule
import com.unorg.nasa.di.ViewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NasaApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@NasaApplication)
            modules(NetworkModule.networkModule,ViewModelModules.viewModels)
        }
    }
}