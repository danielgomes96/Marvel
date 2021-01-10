package com.lucasdias.domain.model

class Image(
    val path: String?,
    val extension: String?
) {
    fun getUrl(): String {
        return "$path.$extension".replace("http", "https")
    }
}
