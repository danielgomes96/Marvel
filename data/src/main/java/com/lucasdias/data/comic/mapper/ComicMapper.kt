package com.lucasdias.data.comic.mapper

import com.lucasdias.data.comic.remote.model.ImageResponse
import com.lucasdias.domain.model.Image

fun ImageResponse.toDomain(): Image {
    return Image(
        path = this.path,
        extension = this.extension
    )
}
