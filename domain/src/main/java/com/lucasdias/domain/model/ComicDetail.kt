package com.lucasdias.domain.model

data class ComicDetail(
    val id: Int?,
    val title: String?,
    val description: String?,
    val pageCount: Int?,
    val prices: List<String>?,
    val characters: List<String>?,
    val stories: List<String>?,
    val creators: List<String>?,
    val images: List<Image>?
)