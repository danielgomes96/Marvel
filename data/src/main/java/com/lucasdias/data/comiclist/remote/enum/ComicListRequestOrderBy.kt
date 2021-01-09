package com.lucasdias.data.comiclist.remote.enum

enum class ComicListRequestOrderBy(val text: String) {
    NAME("name"),
    MODIFIED("modified"),
    REVERSE_NAME("-name"),
    REVERSE_MODIFIED("-modified")
}
