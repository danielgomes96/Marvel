package com.lucasdias.data.comic.remote.enum

enum class ComicListRequestOrderBy(val text: String) {
    NAME("name"),
    MODIFIED("modified"),
    REVERSE_NAME("-name"),
    REVERSE_MODIFIED("-modified")
}
