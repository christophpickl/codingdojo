package com.github.christophpickl.codingdojo.csvviewer

object CsvFormatter {

    private val headerSeparator = "+"
    private val entrySeparator = "|"
    private val underline = "-"
    private val newLine = "\n"

    fun format(csv: CsvTable): String {
        return csv.headers.mapIndexed { index, header ->
            header.ensureLength(csv.columnMaxLengths[index])
        }.joinToString(entrySeparator) + newLine +
            csv.headers.mapIndexed { index, _ ->
                underline.times(csv.columnMaxLengths[index])
            }.joinToString(headerSeparator) + newLine +
            csv.rowData.mapIndexed { _, row ->
                row.mapIndexed { index, rowEntry ->
                    rowEntry.ensureLength(csv.columnMaxLengths[index])
                }.joinToString(entrySeparator)
            }.joinToString(newLine)
    }

    private fun String.ensureLength(desiredLength: Int): String {
        if (length >= desiredLength) {
            return this
        }
        return this + " ".times(desiredLength - length)
    }

    private fun String.times(desiredLength: Int) =
        (1..desiredLength).fold("") { acc, _ -> "$acc$this" }

}
