package com.lucasdias.data.comiclist.remote.mapper

import com.lucasdias.data.comiclist.remote.model.ComicResponse
import com.lucasdias.data.comiclist.remote.model.ComicThumbnailResponse
import com.lucasdias.domain.model.ComicSummary
import com.lucasdias.domain.model.ComicThumbnail

fun List<ComicResponse>.toDomain(): List<ComicSummary> {
    val comicList = mutableListOf<ComicSummary>()

    this.forEach {
        comicList.addJustValidComic(it)
    }

    return comicList
}

fun ComicThumbnailResponse.toDomain(): ComicThumbnail {
    return ComicThumbnail(
        path = this.path,
        extension = this.extension
    )
}

private fun MutableList<ComicSummary>.addJustValidComic(comicResponse: ComicResponse) {
    comicResponse.also {
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
