package com.lucasdias.feature_comic.list

import android.view.View
import android.widget.ProgressBar
import com.google.android.material.snackbar.Snackbar
import com.lucasdias.extensions.animateGoneToVisible
import com.lucasdias.extensions.model.SnackbarProperties
import com.lucasdias.extensions.showSnackbar
import com.lucasdias.feature_comic.R

fun ComicListFragment.showProgressbarForPaginationRequest(
    isNotInitialRequest: Boolean,
    errorViewVisibility: Int,
    progressBar: ProgressBar
) {
    if (isNotInitialRequest && errorViewVisibility != View.VISIBLE) {
        progressBar.animateGoneToVisible()
    }
}

fun ComicListFragment.showErrorSnackbarForPaginationRequest(notInitialRequest: Boolean) {
    if (notInitialRequest) {
        val properties = SnackbarProperties(
            textResId = R.string.default_message_snackbar,
            backgroundResId = R.color.bright_red,
            iconResId = R.drawable.alert_icon,
            duration = Snackbar.LENGTH_LONG
        )
        this.view?.showSnackbar(properties)
    }
}
