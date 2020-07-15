package com.androidarchitecture.utilities

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    private var isLoading = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val lastVisibleItemIndex = layoutManager.findLastVisibleItemPosition()

        if ((
            recyclerView.adapter?.itemCount
                ?: 0
            ) > 0 && lastVisibleItemIndex >
            (recyclerView.adapter?.itemCount?.minus(3) ?: 0) &&
            !isLoading && !isAllLoaded()
        ) {
            setLoadInProgress()
            loadMore() // load next set of data
        }
    }

    fun loadCompleted() {
        isLoading = false
    }

    private fun setLoadInProgress() {
        isLoading = true
    }

    abstract fun loadMore()

    abstract fun isAllLoaded(): Boolean
}
