package com.github.christophpickl.codingdojo.csvviewer

object Formatter {

    private val headerSeparator = "+"
    private val entrySeparator = "|"
    private val underline = "-"
    private val newLine = "\n"

    fun format(csv: Table): String =
        csv.formatTable()

    private fun Table.formatTable() =
        headers.mapIndexed { index, header ->
            header.ensureLength(columnMaxLengths[index])
        }.joinToString(entrySeparator) + newLine +
            headers.mapIndexed { index, _ ->
                underline.times(columnMaxLengths[index])
            }.joinToString(headerSeparator) + newLine +
            rowData.mapIndexed { _, row ->
                row.mapIndexed { index, rowEntry ->
                    rowEntry.ensureLength(columnMaxLengths[index])
                }.joinToString(entrySeparator)
            }.joinToString(newLine)

    private fun String.ensureLength(desiredLength: Int) =
        if (length >= desiredLength) this
        else this + " ".times(desiredLength - length)

    private fun String.times(desiredLength: Int) =
        (1..desiredLength).fold("") { acc, _ -> "$acc$this" }

}
