package com.github.christophpickl.codingdojo.csvviewer

object CsvReader {

    fun read(content: String): CsvTable {
        val lines = content.lines()
        val headers = lines[0].split(";")
        val entriesByColumn = 1.rangeTo(headers.size).map { ArrayList<String>() }
        lines.subList(1, lines.size).forEach { line ->
            val lineData = line.split(";")
            lineData.forEachIndexed { index, entry ->
                entriesByColumn[index] += entry
            }
        }
        val columns = entriesByColumn.mapIndexed { index, entries ->
            CsvColumn(headers[index], entries)
        }
        return CsvTable(columns)
    }

}
