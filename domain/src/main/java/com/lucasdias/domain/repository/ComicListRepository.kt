package com.lucasdias.domain.repository

import com.lucasdias.core.resource.Resource
import com.lucasdias.domain.model.ComicSummary

interface ComicListRepository {
    suspend fun fetch(
        apiPublicKey: String,
        timesmap: String,
        hash: String
    ): Resource<List<ComicSummary>>
}
