package com.github.christophpickl.codingdojo.csvviewer.logic

object Formatter {

    private const val headerSeparator = "+"
    private const val entrySeparator = "|"
    private const val underline = "-"
    private const val newLine = "\n"

    fun format(table: Table, pageRequest: PageRequest = PageRequest.all): String =
        table.enhanceNumberColumn().formatTable(pageRequest)

    private fun Table.enhanceNumberColumn() = Table(
        headers = ArrayList<String>().apply {
            this += "No."
            this += headers
        },
        rowData = ArrayList<List<String>>().apply {
            this += rowData.mapIndexed { index, list ->
                ArrayList<String>().apply {
                    this += "${index + 1}."
                    this += list
                }
            }
        }
    )

    private fun Table.formatTable(pageRequest: PageRequest) =
        headers.mapIndexed { index, header ->
            header.ensureLength(columnMaxLengths[index])
        }.joinToString(entrySeparator) + newLine +
            headers.mapIndexed { index, _ ->
                underline.times(columnMaxLengths[index])
            }.joinToString(headerSeparator) + newLine +
            rowData.paginated(pageRequest).mapIndexed { _, row ->
                row.mapIndexed { index, rowEntry ->
                    rowEntry.ensureLength(columnMaxLengths[index])
                }.joinToString(entrySeparator)
            }.joinToString(newLine)

    private fun String.ensureLength(desiredLength: Int) =
        if (length >= desiredLength) this
        else this + " ".times(desiredLength - length)

    private fun String.times(desiredLength: Int) =
        (1..desiredLength).fold("") { acc, _ -> "$acc$this" }

    private fun <E> List<E>.paginated(pageRequest: PageRequest) =
        subList(pageRequest.skip, Math.min(pageRequest.skip + pageRequest.take, size))
}
