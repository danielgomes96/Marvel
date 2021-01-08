package com.lucasdias.extensions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.scrollSetup(
    layoutManager: LinearLayoutManager,
    methodToInvokeAtEnd: () -> Unit
) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (isTheListEnd(layoutManager, dy)) {
                methodToInvokeAtEnd()
            }
        }
    })
}

private fun isTheListEnd(layoutManager: LinearLayoutManager, dy: Int): Boolean {
    with(layoutManager) {
        val visibleItemCount = childCount
        val totalItemCount = itemCount
        val firstVisibleItemPosition = findFirstVisibleItemPosition()
        return@isTheListEnd visibleItemCount + firstVisibleItemPosition >= totalItemCount && dy > 0
    }
}
