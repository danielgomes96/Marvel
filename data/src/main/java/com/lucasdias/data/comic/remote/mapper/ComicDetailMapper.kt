package com.lucasdias.data.comic.remote.mapper

import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.fromHtml
import com.lucasdias.data.comic.remote.model.CharactersResponse
import com.lucasdias.data.comic.remote.model.ComicDetailResponse
import com.lucasdias.data.comic.remote.model.CreatorsResponse
import com.lucasdias.data.comic.remote.model.PriceResponse
import com.lucasdias.data.comic.remote.model.StoriesResponse
import com.lucasdias.domain.model.ComicDetail

fun ComicDetailResponse.toDomain(): ComicDetail {
    return ComicDetail(
        id = id,
        title = title,
        description = fromHtml(description.orEmpty(), HtmlCompat.FROM_HTML_MODE_LEGACY).toString(),
        pageCount = pageCount,
        prices = prices?.map { it.toDomain() },
        characters = characters?.toDomain(),
        stories = stories?.toDomain(),
        creators = creators?.toDomain(),
        images = images?.map { it.toDomain() }
    )
}

fun CharactersResponse.toDomain(): List<String>? {
    return mutableListOf<String>().also { list ->
        items?.forEach {
            list.add(it.name.orEmpty())
        }
    }
}

fun StoriesResponse.toDomain(): List<String>? {
    return mutableListOf<String>().also { list ->
        items?.forEach {
            list.add(it.name.orEmpty())
        }
    }
}

fun CreatorsResponse.toDomain(): List<String>? {
    return mutableListOf<String>().also { list ->
        items?.forEach {
            list.add(it.name.orEmpty())
        }
    }
}

fun PriceResponse.toDomain(): String = this.price.toString()
