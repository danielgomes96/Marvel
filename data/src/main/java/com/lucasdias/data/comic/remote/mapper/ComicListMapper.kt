package com.lucasdias.data.comic.remote.mapper

import com.lucasdias.data.comic.remote.model.ComicSummaryResponse
import com.lucasdias.domain.model.ComicSummary

fun List<ComicSummaryResponse>.toDomain(): List<ComicSummary> {
    val comicList = mutableListOf<ComicSummary>()

    this.forEach {
        comicList.addJustValidComic(it)
    }

    return comicList
}

private fun MutableList<ComicSummary>.addJustValidComic(comicSummaryResponse: ComicSummaryResponse) {
    comicSummaryResponse.also {
        if (
            it.id != null &&
            it.title != null &&
            it.thumbnail != null &&
            it.thumbnail.path?.contains("image_not_available") != true
        ) {
            val comic = ComicSummary(
                id = it.id,
                title = it.title,
                thumbnail = it.thumbnail.toDomain()
            )
            this.add(comic)
        }
    }
}
