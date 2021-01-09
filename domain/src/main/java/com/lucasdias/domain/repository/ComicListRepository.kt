package com.lucasdias.domain.repository

interface ComicListRepository {
    suspend fun fetch(
        apiPublicKey: String,
        timesmap: String,
        hash: String
    )
}
