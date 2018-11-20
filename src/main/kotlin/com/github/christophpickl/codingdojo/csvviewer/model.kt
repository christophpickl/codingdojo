package com.github.christophpickl.codingdojo.csvviewer

data class CsvTable(
    val columns: List<CsvColumn>
) {
    init {
        require(columns.isNotEmpty()) { "At least one column must be defined!" }
        require(columns.map { it.rows.size }.distinct().size == 1) { "Columns must have all same row size!" }
    }
}

data class CsvColumn(
    val header: String,
    val rows: List<String>
)
