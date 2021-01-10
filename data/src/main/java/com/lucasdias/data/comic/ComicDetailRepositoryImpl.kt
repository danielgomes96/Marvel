package com.lucasdias.data.comic

import android.util.Log
import com.lucasdias.core.resource.Resource
import com.lucasdias.data.comic.remote.ComicDetailService
import com.lucasdias.data.comic.remote.mapper.toDomain
import com.lucasdias.data.comic.remote.model.ComicDetailResponse
import com.lucasdias.data.comic.remote.model.ComicSummaryResponse
import com.lucasdias.data.comic.remote.model.GlobalResponse
import com.lucasdias.domain.model.ComicDetail
import com.lucasdias.domain.model.ComicSummary
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
    ): Resource<ComicDetail> {
        val response: Resource<Response<GlobalResponse<ComicDetailResponse>>> =
            Resource.of {
                service.fetchComicDetail(
                    comicId = comicId,
                    apiKey = apiPublicKey,
                    timestamp = timesmap,
                    hash = hash
                )
            }
        Log.i("Comic detail response", response.value()?.body().toString())
        return response.getTreatedResponse()
    }

    private fun Resource<Response<GlobalResponse<ComicDetailResponse>>>.getTreatedResponse(): Resource<ComicDetail> {
        this.value()?.body()?.data?.results?.first()?.let {
            val comicDetail = it.toDomain()

            return Resource.Success(comicDetail)
        } ?: run {
            return Resource.Error(this.error() ?: Exception())
        }
    }
}
