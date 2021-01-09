package com.lucasdias.domain.usecase

import com.lucasdias.domain.BuildConfig
import com.lucasdias.domain.repository.ComicDetailRepository

class FetchComicDetail(
    private val getHash: GetHash,
    private val comicDetailRepository: ComicDetailRepository
) {

    suspend operator fun invoke(comicId: String) {
        val timesmap = System.currentTimeMillis().toString()
        val apiPublicKey = BuildConfig.MARVEL_API_PUBLIC_KEY
        val apiPrivateKey = BuildConfig.MARVEL_API_PRIVATE_KEY

        val hash = getHash(timesmap, apiPrivateKey, apiPublicKey)

        return comicDetailRepository.fetch(comicId, apiPublicKey, timesmap, hash)
    }
}
