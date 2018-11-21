package com.github.christophpickl.codingdojo.csvviewer

object Formatter {

    private val headerSeparator = "+"
    private val entrySeparator = "|"
    private val underline = "-"
    private val newLine = "\n"

    fun format(csv: Table, pageRequest: PageRequest = PageRequest.all): String =
        csv.formatTable(pageRequest)

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
