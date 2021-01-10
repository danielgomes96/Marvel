package com.lucasdias.feature_comic.detail

import com.lucasdias.base.presentation.BaseViewModel
import com.lucasdias.core.resource.Resource
import com.lucasdias.domain.model.ComicDetail
import com.lucasdias.domain.usecase.FetchComicDetail
import kotlinx.coroutines.CoroutineDispatcher

class ComicDetailViewModel(
    private val fetchComicDetail: FetchComicDetail,
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<ComicDetail>(coroutineDispatcher) {

    private var comicId: String = ""

    override suspend fun request(): Resource<ComicDetail> {
        fetchComicDetail(comicId)
        return Resource.Success(ComicDetail(0))
    }

    fun setArgumentForRequest(comicId: Int) {
        this.comicId = comicId.toString()
    }
}
