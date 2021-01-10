package com.lucasdias.data.comic.remote.model

data class GlobalResponse<T>(
    val code: Int?,
    val status: String?,
    val data: DataResponse<T>?
)
