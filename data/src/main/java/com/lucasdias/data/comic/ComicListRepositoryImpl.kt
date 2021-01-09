package com.lucasdias.data.comic

import com.lucasdias.core.resource.Resource
import com.lucasdias.data.comic.remote.ComicListService
import com.lucasdias.data.comic.remote.enum.ComicListRequestOrderBy
import com.lucasdias.data.comic.remote.mapper.toDomain
import com.lucasdias.data.comic.remote.model.ComicGlobalResponse
import com.lucasdias.domain.model.Comic
import com.lucasdias.domain.repository.ComicListRepository
import retrofit2.Response

class ComicListRepositoryImpl(
    private val service: ComicListService
) : ComicListRepository {

    override suspend fun fetch(
        apiPublicKey: String,
        timesmap: String,
        hash: String
    ): Resource<List<Comic>> {
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

        return response.getTreatedResponse()
    }

    private fun Resource<Response<ComicGlobalResponse>>.getTreatedResponse(): Resource<List<Comic>> {
        this.value()?.body()?.data?.results?.let {
            val characterList = it.toDomain()

            return Resource.Success(characterList)
        } ?: run {
            return Resource.Error(this.error() ?: Exception())
        }
    }
}
