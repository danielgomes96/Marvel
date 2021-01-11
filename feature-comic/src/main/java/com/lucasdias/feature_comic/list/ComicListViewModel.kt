package com.lucasdias.feature_comic.list

import com.lucasdias.base.presentation.BaseViewModel
import com.lucasdias.core.resource.Resource
import com.lucasdias.domain.model.ComicSummary
import com.lucasdias.domain.usecase.FetchComicList
import kotlinx.coroutines.CoroutineDispatcher

class ComicListViewModel(
    private val fetchComicList: FetchComicList,
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<List<ComicSummary>>(coroutineDispatcher) {

    private var requestIsBlocked = false

    override suspend fun request(): Resource<List<ComicSummary>> {
        return if (requestIsBlocked) {
            requestIsBlocked = false
            Resource.Success(emptyList())
        } else {
            fetchComicList()
        }
    }

    fun requestNextPage() {
        if (isNotLoading()) {
            executeRequest()
        }
    }

    fun blockNextRequest() {
        requestIsBlocked = true
    }
}
