package com.lucasdias.marvelcomics

import android.app.Application
import com.lucasdias.extensions.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.logger.Logger

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            logger(dependencyInjectionLogger())
            modules(
                listOf()
            )
        }
    }

    private fun dependencyInjectionLogger(): Logger =
        if (BuildConfig.DEBUG) AndroidLogger() else EmptyLogger()
}
