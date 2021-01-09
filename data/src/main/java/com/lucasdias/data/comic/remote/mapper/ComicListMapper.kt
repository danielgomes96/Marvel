package com.lucasdias.data.comic.remote.mapper

import com.lucasdias.data.comic.remote.model.ComicResponse
import com.lucasdias.data.comic.remote.model.ComicThumbnailResponse
import com.lucasdias.domain.model.Comic
import com.lucasdias.domain.model.ComicThumbnail

fun List<ComicResponse>.toDomain(): List<Comic> {
    val comicList = mutableListOf<Comic>()

    this.forEach {
        if (it.canAddComic()) {
            val comic = Comic(
                id = it.id,
                title = it.title,
                thumbnail = it.thumbnail?.toDomain()
            )
            comicList.add(comic)
        }
    }

    return comicList
}

fun ComicThumbnailResponse.toDomain(): ComicThumbnail {
    return ComicThumbnail(
        path = this.path,
        extension = this.extension
    )
}

private fun ComicResponse.canAddComic(): Boolean {
    return thumbnail != null &&
            thumbnail.path?.contains("image_not_available") != true
}
