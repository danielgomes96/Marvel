package com.lucasdias.data.comic.remote

import com.lucasdias.data.comic.remote.model.ComicGlobalResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicListService {
    @GET("comics")
    suspend fun fetchComicList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("orderBy") orderBy: String,
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String
    ): Response<ComicGlobalResponse>
}
