package com.github.christophpickl.codingdojo.csvviewer

object Reader {

    fun read(lines: List<String>): Table {
        val headers = lines[0].split(";")
        val rowData = ArrayList<List<String>>(lines.size - 1)
        lines.subList(1, lines.size).forEach { line ->
            rowData += line.split(";")
        }
        return Table(
            headers = headers,
            rowData = rowData
        )
    }

}
