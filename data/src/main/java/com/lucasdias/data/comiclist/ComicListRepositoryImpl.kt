package com.lucasdias.data.comiclist

import com.lucasdias.core.resource.Resource
import com.lucasdias.data.comiclist.remote.ComicListService
import com.lucasdias.data.comiclist.remote.enum.ComicListRequestOrderBy
import com.lucasdias.data.comiclist.remote.mapper.toDomain
import com.lucasdias.data.comiclist.remote.model.ComicGlobalResponse
import com.lucasdias.domain.model.ComicSummary
import com.lucasdias.domain.repository.ComicListRepository
import retrofit2.Response

class ComicListRepositoryImpl(
    private val service: ComicListService
) : ComicListRepository {

    private var currentComicsCount = 0
    private val limitOfComicsPerRequest = 40

    override suspend fun fetch(
        apiPublicKey: String,
        timesmap: String,
        hash: String
    ): Resource<List<ComicSummary>> {
        val response: Resource<Response<ComicGlobalResponse>> =
            Resource.of {
                service.fetchComicList(
                    limit = limitOfComicsPerRequest,
                    offset = currentComicsCount,
                    orderBy = ComicListRequestOrderBy.MODIFIED.text,
                    apiKey = apiPublicKey,
                    timestamp = timesmap,
                    hash = hash
                )
            }

        return response.getTreatedResponse()
    }

    private fun Resource<Response<ComicGlobalResponse>>.getTreatedResponse(): Resource<List<ComicSummary>> {
        this.value()?.body()?.data?.results?.let {
            val comicList = it.toDomain()

            updateCurrentComicsCount()

            return Resource.Success(comicList)
        } ?: run {
            return Resource.Error(this.error() ?: Exception())
        }
    }

    private fun updateCurrentComicsCount() {
        currentComicsCount += limitOfComicsPerRequest
    }
}
