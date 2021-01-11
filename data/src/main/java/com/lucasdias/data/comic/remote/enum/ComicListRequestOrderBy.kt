package com.lucasdias.data.comic.remote.enum

enum class ComicListRequestOrderBy(val text: String) {
    TITLE("title"),
    MODIFIED("modified"),
    REVERSE_TITLE("-title"),
    REVERSE_MODIFIED("-modified")
}
