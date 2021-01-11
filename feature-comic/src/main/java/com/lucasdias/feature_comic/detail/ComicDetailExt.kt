package com.lucasdias.feature_comic.detail


fun Int?.toStringIfPositiveOrEmptyString(): String {
    return if (this != null && this > 0) {
        this.toString()
    }
    else {
        ""
    }
}