package com.lucasdias.data.comic

import android.util.Log
import com.lucasdias.core.resource.Resource
import com.lucasdias.data.comic.remote.ComicListService
import com.lucasdias.data.comic.remote.enum.ComicListRequestOrderBy
import com.lucasdias.data.comic.remote.model.ComicGlobalResponse
import com.lucasdias.domain.repository.ComicListRepository
import retrofit2.Response

class ComicListRepositoryImpl(
    private val service: ComicListService
) : ComicListRepository {

    override suspend fun fetch(
        apiPublicKey: String,
        timesmap: String,
        hash: String
    ) {
        val response: Resource<Response<ComicGlobalResponse>> =
            Resource.of {
                service.fetchComicList(
                    limit = 40,
                    offset = 40,
                    orderBy = ComicListRequestOrderBy.MODIFIED.text,
                    apiKey = apiPublicKey,
                    timestamp = timesmap,
                    hash = hash
                )
            }
        Log.i("Comic List API Response", response.value()?.body()?.data?.results.toString())
    }
}
