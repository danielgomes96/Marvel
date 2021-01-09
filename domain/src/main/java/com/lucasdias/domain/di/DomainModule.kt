package com.lucasdias.domain.di

import com.lucasdias.domain.repository.ComicDetailRepository
import com.lucasdias.domain.repository.ComicListRepository
import com.lucasdias.domain.usecase.FetchComicDetail
import com.lucasdias.domain.usecase.FetchComicList
import com.lucasdias.domain.usecase.GetHash
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val domainModule = module {

    factory {
        GetHash()
    }

    factory {
        FetchComicList(
            get<GetHash>(),
            get<ComicListRepository>()
        )
    }

    factory {
        FetchComicDetail(
            get<GetHash>(),
            get<ComicDetailRepository>()
        )
    }
}
