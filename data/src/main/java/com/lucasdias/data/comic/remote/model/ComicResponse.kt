package com.lucasdias.data.comic.remote.model

data class ComicResponse(
    val id: Int?,
    val name: String?,
    val thumbnail: ComicThumbnailResponse?
)
