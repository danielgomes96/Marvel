package com.lucasdias.domain.usecase

import com.lucasdias.core.resource.Resource
import com.lucasdias.domain.BuildConfig
import com.lucasdias.domain.model.ComicDetail
import com.lucasdias.domain.repository.ComicDetailRepository

class FetchComicDetail(
    private val getHash: GetHash,
    private val comicDetailRepository: ComicDetailRepository
) {

    suspend operator fun invoke(comicId: String): Resource<ComicDetail> {
        val timesMap = System.currentTimeMillis().toString()
        val apiPublicKey = BuildConfig.MARVEL_API_PUBLIC_KEY
        val apiPrivateKey = BuildConfig.MARVEL_API_PRIVATE_KEY

        val hash = getHash(timesMap, apiPrivateKey, apiPublicKey)

        return comicDetailRepository.fetch(comicId, apiPublicKey, timesMap, hash)
    }
}
