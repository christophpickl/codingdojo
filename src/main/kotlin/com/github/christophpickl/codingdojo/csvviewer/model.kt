package com.github.christophpickl.codingdojo.csvviewer

data class CsvTable(
    val headers: List<String>,
    val rowData: List<List<String>>
) {

    val maxLength: Int = Math.max(
        headers.map { it.length }.max() ?: 0,
        rowData.flatten().map { it.length }.max() ?: 0
    )

    init {
        require(headers.isNotEmpty()) { "At least one column must be defined!" }
        require(rowData.map { it.size }.distinct().size == 1) { "Rows must have all same column size!" }
    }
}
