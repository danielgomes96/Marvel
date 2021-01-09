package com.lucasdias.data.di

import com.lucasdias.data.BuildConfig.MARVEL_API_URL
import com.lucasdias.data.comic.ComicListRepositoryImpl
import com.lucasdias.data.comic.remote.ComicListService
import com.lucasdias.domain.repository.ComicListRepository
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val MARVEL_RETROFIT = "MARVEL_RETROFIT"

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val dataModule = module {
    factory {
        ComicListRepositoryImpl(
            get<ComicListService>()
        ) as ComicListRepository
    }

    factory {
        getComicListService(
            get<Retrofit>(named(MARVEL_RETROFIT))
        )
    }

    single(named(MARVEL_RETROFIT)) {
        createMarvelRetrofit(
            get<OkHttpClient>()
        )
    }

    factory {
        createOkHttpClient()
    }
}

private fun getComicListService(retrofit: Retrofit): ComicListService =
    retrofit.create(ComicListService::class.java)

private fun createMarvelRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(MARVEL_API_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private fun createOkHttpClient(): OkHttpClient {
    val timeoutInSeconds = 10L
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(timeoutInSeconds, TimeUnit.SECONDS)
        .readTimeout(timeoutInSeconds, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}
