package com.github.christophpickl.codingdojo.csvviewer

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
