package com.lucasdias.domain.usecase

import com.lucasdias.domain.BuildConfig
import com.lucasdias.domain.repository.ComicListRepository

class FetchComicList(
    private val getHash: GetHash,
    private val comicListRepository: ComicListRepository
) {

    suspend operator fun invoke() {
        val timesmap = System.currentTimeMillis().toString()
        val apiPublicKey = BuildConfig.MARVEL_API_PUBLIC_KEY
        val apiPrivateKey = BuildConfig.MARVEL_API_PRIVATE_KEY

        val hash = getHash(timesmap, apiPrivateKey, apiPublicKey)

        comicListRepository.fetch(apiPublicKey, timesmap, hash)
    }
}
