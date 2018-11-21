package com.github.christophpickl.codingdojo.csvviewer

data class PageRequest(
    val skip: Int,
    val take: Int
) {
    companion object {
        val all = PageRequest(skip = 0, take = Integer.MAX_VALUE)
    }
}
