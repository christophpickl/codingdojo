package com.github.christophpickl.codingdojo.csvviewer.logic

class Paginator(
    private val pageSize: Int,
    totalRows: Int
) {

    private var currentPage = 0

    val maxPage = Math.ceil(totalRows / pageSize.toDouble()).toInt() - 1
    val currentPageRequest get() = PageRequest(pageSize * currentPage, pageSize)
    val pageDisplay get() = "Page ${currentPage + 1} of ${maxPage + 1}"

    fun nextPage() {
        if (currentPage != maxPage) {
            currentPage++
        }
    }

    fun previousPage() {
        if (currentPage != 0) {
            currentPage--
        }
    }

    fun firstPage() {
        currentPage = 0
    }

    fun lastPage() {
        currentPage = maxPage
    }

    fun selectPage(requestedPage: Int) {
        require(requestedPage in 0..maxPage)
        currentPage = requestedPage
    }

}

data class PageRequest(
    val skip: Int,
    val take: Int
) {

    companion object {
        val all = PageRequest(skip = 0, take = Integer.MAX_VALUE)
    }

    init {
        require(skip >= 0) { "Paramter 'skip' must not be negative! ($skip)" }
        require(take >= 0) { "Paramter 'take' must not be negative! ($take)" }
    }

}