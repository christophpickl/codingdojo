package com.github.christophpickl.codingdojo.csvviewer.logic

object Reader {

    private const val csvSeparator = ";"
    
    fun read(lines: List<String>): Table {
        if (lines.isEmpty()) {
            return Table.empty
        }
        val headers = lines[0].split(csvSeparator)
        val rowData = ArrayList<List<String>>(lines.size - 1)
        lines.subList(1, lines.size).filter { it.isNotBlank() }.forEach { line ->
            rowData += line.split(csvSeparator)
        }
        return Table(
            headers = headers,
            rowData = rowData
        )
    }

}
