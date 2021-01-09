package com.lucasdias.data.comiclist.remote.model

data class ComicResponse(
    val id: Int?,
    val title: String?,
    val thumbnail: ComicThumbnailResponse?
)
