package com.lucasdias.domain.repository

import com.lucasdias.core.resource.Resource
import com.lucasdias.domain.model.ComicDetail

interface ComicDetailRepository {
    suspend fun fetch(
        comicId: String,
        apiPublicKey: String,
        timesmap: String,
        hash: String
    ): Resource<ComicDetail>
}
