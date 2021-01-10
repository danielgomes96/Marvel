package com.lucasdias.data.comic.remote

import com.lucasdias.data.comic.remote.model.ComicDetailResponse
import com.lucasdias.data.comic.remote.model.GlobalResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicDetailService {
    @GET("comics/{comic_id}")
    suspend fun fetchComicDetail(
        @Path("comic_id") comicId: String,
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String
    ): Response<GlobalResponse<ComicDetailResponse>>
}
