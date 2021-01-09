package com.lucasdias.data.comic.remote.model

data class ComicResponse(
    val id: Int?,
    val title: String?,
    val thumbnail: ComicThumbnailResponse?
)
