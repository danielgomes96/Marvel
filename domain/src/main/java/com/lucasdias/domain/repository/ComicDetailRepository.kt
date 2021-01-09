package com.lucasdias.domain.repository

interface ComicDetailRepository {
    suspend fun fetch(
        comicId: String,
        apiPublicKey: String,
        timesmap: String,
        hash: String
    )
}
