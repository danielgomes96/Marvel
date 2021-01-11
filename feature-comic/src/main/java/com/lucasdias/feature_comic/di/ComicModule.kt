package com.lucasdias.feature_comic.di

import com.lucasdias.domain.usecase.FetchComicDetail
import com.lucasdias.domain.usecase.FetchComicList
import com.lucasdias.feature_comic.detail.ComicDetailViewModel
import com.lucasdias.feature_comic.detail.adapter.ComicDetailAdapter
import com.lucasdias.feature_comic.list.ComicListAdapter
import com.lucasdias.feature_comic.list.ComicListViewModel
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val CHARACTER_ADAPTER = "CHARACTER_ADAPTER"
const val PRICE_ADAPTER = "PRICE_ADAPTER"
const val STORY_ADAPTER = "STORY_ADAPTER"
const val CREATOR_ADAPTER = "CREATOR_ADAPTER"

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val comicModule = module {

    viewModel {
        ComicListViewModel(
            get<FetchComicList>(),
            get<CoroutineDispatcher>()
        )
    }

    viewModel {
        ComicDetailViewModel(
            get<FetchComicDetail>(),
            get<CoroutineDispatcher>()
        )
    }

    factory { (navigateToComicDetailAction: ((Int) -> Unit)) ->
        ComicListAdapter(navigateToComicDetailAction)
    }

    factory(named(CHARACTER_ADAPTER)) {
        ComicDetailAdapter()
    }

    factory(named(PRICE_ADAPTER)) {
        ComicDetailAdapter()
    }

    factory(named(STORY_ADAPTER)) {
        ComicDetailAdapter()
    }

    factory(named(CREATOR_ADAPTER)) {
        ComicDetailAdapter()
    }
}
