package com.lucasdias.feature_comic.di

import com.lucasdias.domain.usecase.FetchComicList
import com.lucasdias.feature_comic.list.ComicListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val comicModule = module {

    viewModel {
        ComicListViewModel(
            get<FetchComicList>()
        )
    }
}
