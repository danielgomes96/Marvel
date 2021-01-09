package com.lucasdias.feature_comic.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasdias.domain.usecase.FetchComicList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ComicListViewModel(
    private val fetchComicList: FetchComicList
) : ViewModel() {

    fun fetch() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchComicList()
        }
    }
}
