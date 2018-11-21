package com.github.christophpickl.codingdojo.csvviewer

data class Table(
    val headers: List<String>,
    val rowData: List<List<String>>
) {

    companion object {
        val empty = Table(emptyList(), emptyList())
    }

    init {
        val sizes = rowData.map { it.size }.distinct()
        require(sizes.size <= 1) {
            "Rows must have all same column size, but was: $sizes"
        }
    }

    private val allLines = ArrayList<List<String>>().apply {
        this += headers
        this += rowData
    }

    val columnMaxLengths: List<Int> = (0 until headers.size).map { colNumber ->
        allLines.map { it[colNumber].length }.max() ?: 0
    }

}
