package com.github.christophpickl.codingdojo.csvviewer

object CsvReader {

    fun read(lines: List<String>): CsvTable {
        val headers = lines[0].split(";")
        val rowData = ArrayList<List<String>>(lines.size - 1)
        lines.subList(1, lines.size).forEach { line ->
            rowData += line.split(";")
        }
        return CsvTable(
            headers = headers,
            rowData = rowData
        )
    }

}
