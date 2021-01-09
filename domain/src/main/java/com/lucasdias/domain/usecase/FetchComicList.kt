package com.lucasdias.domain.usecase

import com.lucasdias.core.resource.Resource
import com.lucasdias.domain.BuildConfig
import com.lucasdias.domain.model.Comic
import com.lucasdias.domain.repository.ComicListRepository

class FetchComicList(
    private val getHash: GetHash,
    private val comicListRepository: ComicListRepository
) {

    suspend operator fun invoke(): Resource<List<Comic>> {
        val timesmap = System.currentTimeMillis().toString()
        val apiPublicKey = BuildConfig.MARVEL_API_PUBLIC_KEY
        val apiPrivateKey = BuildConfig.MARVEL_API_PRIVATE_KEY

        val hash = getHash(timesmap, apiPrivateKey, apiPublicKey)

        return comicListRepository.fetch(apiPublicKey, timesmap, hash)
    }
}
