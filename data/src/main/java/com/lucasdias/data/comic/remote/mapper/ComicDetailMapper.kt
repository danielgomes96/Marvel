package com.lucasdias.data.comic.remote.mapper

import com.lucasdias.data.comic.remote.model.CharactersResponse
import com.lucasdias.data.comic.remote.model.ComicDetailResponse
import com.lucasdias.data.comic.remote.model.CreatorsResponse
import com.lucasdias.data.comic.remote.model.StoriesResponse
import com.lucasdias.data.comic.remote.model.PriceResponse
import com.lucasdias.domain.model.ComicDetail

fun ComicDetailResponse.toDomain(): ComicDetail {
    return ComicDetail(
        id = id,
        title = title,
        description = description,
        pageCount = pageCount,
        prices = prices?.map { it.toDomain() },
        characters = characters?.toDomain(),
        stories = stories?.toDomain(),
        creators = creators?.toDomain(),
        images = images?.map { it.toDomain() }
    )
}

fun CharactersResponse.toDomain(): List<String>? {
    val domainModel = mutableListOf<String>().also {
        this.items?.map {
            it.name
        }
    }
    return domainModel
}

fun StoriesResponse.toDomain(): List<String>? {
    val domainModel = mutableListOf<String>().also {
        this.items?.map {
            it.name
        }
    }
    return domainModel
}

fun CreatorsResponse.toDomain(): List<String>? {
    val domainModel = mutableListOf<String>().also {
        this.items?.map {
            it.name
        }
    }
    return domainModel
}

fun PriceResponse.toDomain(): String = this.price.toString()