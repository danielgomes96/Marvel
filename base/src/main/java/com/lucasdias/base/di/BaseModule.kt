package com.lucasdias.base.di

import com.lucasdias.base.connectivity.Connectivity
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val baseModule = module {

    factory {
        getCoroutinesDispatchersIo()
    }

    single {
        Connectivity(androidApplication())
    }
}

private fun getCoroutinesDispatchersIo() = Dispatchers.IO
