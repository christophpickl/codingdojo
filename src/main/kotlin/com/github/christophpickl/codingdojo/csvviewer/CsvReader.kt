package com.github.christophpickl.codingdojo.csvviewer

object CsvReader {
    fun read(content: String): CsvTable {
        return CsvTable(listOf(CsvColumn("x", emptyList())))
    }
}
