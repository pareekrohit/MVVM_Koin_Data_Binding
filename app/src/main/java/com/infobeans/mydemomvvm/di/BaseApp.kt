package com.infobeans.mydemomvvm.di

import android.app.Application
import com.infobeans.mydemomvvm.di.koin.module
/*import dagger.hilt.android.HiltAndroidApp*/
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import kotlin.text.Typography.dagger

/*@HiltAndroidApp*/
class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApp)
            modules(module)
            /*modules(listOf(repositoryModule, viewModelModule, retrofitModule, apiModule))*/
        }


    }

    companion object {
        lateinit var instance: BaseApp

    }
}