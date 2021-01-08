package com.lucasdias.core.di

import com.lucasdias.core.connectivity.Connectivity
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val coreModule = module {

    single {
        Connectivity(androidApplication())
    }
}
