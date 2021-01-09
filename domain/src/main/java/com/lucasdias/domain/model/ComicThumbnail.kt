package com.lucasdias.domain.model

class ComicThumbnail(
    val path: String?,
    val extension: String?
) {
    fun getUrl(): String {
        return "$path.$extension".replace("http", "https")
    }
}
