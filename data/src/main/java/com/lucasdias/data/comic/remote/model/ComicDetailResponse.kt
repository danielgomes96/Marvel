package com.lucasdias.data.comic.remote.model

data class ComicDetailResponse(
    val id: Int?,
    val title: String?,
    val description: String?,
    val pageCount: Int?,
    val prices: List<PriceResponse>?,
    val characters: CharactersResponse?,
    val stories: StoriesResponse?,
    val creators: CreatorsResponse?,
    val images: List<ImageResponse?>?
)
