package com.lucasdias.data.comic

import com.lucasdias.core.resource.Resource
import com.lucasdias.data.comic.remote.ComicListService
import com.lucasdias.data.comic.remote.enum.ComicListRequestOrderBy
import com.lucasdias.data.comic.remote.mapper.toDomain
import com.lucasdias.data.comic.remote.model.ComicSummaryResponse
import com.lucasdias.data.comic.remote.model.GlobalResponse
import com.lucasdias.domain.model.ComicSummary
import com.lucasdias.domain.repository.ComicListRepository
import retrofit2.Response

class ComicListRepositoryImpl(
    private val service: ComicListService
) : ComicListRepository {

    private var currentRequestedComicsCount = 0
    private val limitOfComicsPerRequest = 100
    private val currentComicList = mutableListOf<ComicSummary>()

    override suspend fun fetch(
        apiPublicKey: String,
        timesmap: String,
        hash: String
    ): Resource<List<ComicSummary>> {
        val response: Resource<Response<GlobalResponse<ComicSummaryResponse>>> =
            Resource.of {
                service.fetchComicList(
                    limit = limitOfComicsPerRequest,
                    offset = currentRequestedComicsCount,
                    orderBy = ComicListRequestOrderBy.MODIFIED.text,
                    apiKey = apiPublicKey,
                    timestamp = timesmap,
                    hash = hash
                )
            }

        return response.getTreatedResponse()
    }

    private fun Resource<Response<GlobalResponse<ComicSummaryResponse>>>.getTreatedResponse(): Resource<List<ComicSummary>> {
        this.value()?.body()?.data?.results?.let {
            val comicList = it.toDomain().getOnlyNotRepeatedItems()

            updateCurrentComicsCount()

            return Resource.Success(comicList)
        } ?: run {
            return Resource.Error(this.error() ?: Exception())
        }
    }

    private fun List<ComicSummary>.getOnlyNotRepeatedItems(): List<ComicSummary> {
        val currentItemsId = currentComicList.map { it.id }.toSet()
        val notRepeatedItems = this.filter { currentItemsId.contains(it.id).not() }

        this@ComicListRepositoryImpl.currentComicList.addAll(notRepeatedItems)

        return notRepeatedItems
    }

    private fun updateCurrentComicsCount() {
        currentRequestedComicsCount += limitOfComicsPerRequest
    }
}
