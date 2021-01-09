package com.lucasdias.data.comicdetail

import android.util.Log
import com.lucasdias.core.resource.Resource
import com.lucasdias.data.comicdetail.remote.ComicDetailService
import com.lucasdias.domain.repository.ComicDetailRepository
import retrofit2.Response

class ComicDetailRepositoryImpl(
    private val service: ComicDetailService
) : ComicDetailRepository {

    override suspend fun fetch(
        comicId: String,
        apiPublicKey: String,
        timesmap: String,
        hash: String
    ) {
        val response: Resource<Response<Any>> =
            Resource.of {
                service.fetchComicList(
                    comicId = comicId,
                    apiKey = apiPublicKey,
                    timestamp = timesmap,
                    hash = hash
                )
            }
        Log.i("Comic detail response", response.value()?.body().toString())
    }
}
