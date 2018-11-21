package com.github.christophpickl.codingdojo.csvviewer

class CsvViewer(
    private val table: Table,
    private val pageSize: Int
) {
    companion object {
        const val DEFAULT_PAGE_SIZE = 10
    }

    fun view() {
        println(Formatter.format(table))
    }

}
