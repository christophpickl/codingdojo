package com.github.christophpickl.codingdojo.csvviewer

data class Table(
    val headers: List<String>,
    val rowData: List<List<String>>
) {

    init {
        require(headers.isNotEmpty()) { "At least one column must be defined!" }
        require(rowData.map { it.size }.distinct().size == 1) { "Rows must have all same column size!" }
    }

    private val allLines = ArrayList<List<String>>().apply {
        this += headers
        this += rowData
    }

    val columnMaxLengths: List<Int> = (0 until headers.size).map { colNumber ->
        allLines.map { it[colNumber].length }.max() ?: 0
    }

}
