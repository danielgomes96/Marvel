package com.lucasdias.data.comic.remote.model

data class ComicGlobalResponse(
    val code: Int?,
    val status: String?,
    val data: ComicDataResponse?
)
