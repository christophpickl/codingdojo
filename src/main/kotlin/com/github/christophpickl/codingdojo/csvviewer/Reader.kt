package com.github.christophpickl.codingdojo.csvviewer

object Reader {

    fun read(lines: List<String>): Table {
        if (lines.isEmpty()) {
            return Table.empty
        }
        val headers = lines[0].split(";")
        val rowData = ArrayList<List<String>>(lines.size - 1)
        lines.subList(1, lines.size).filter { it.isNotBlank() }.forEach { line ->
            rowData += line.split(";")
        }
        return Table(
            headers = headers,
            rowData = rowData
        )
    }

}
