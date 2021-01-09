package com.lucasdias.data.comic.remote.model

data class ComicDataResponse(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<ComicResponse>?
)