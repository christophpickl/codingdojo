package com.github.christophpickl.codingdojo.csvviewer

object CsvFormatter {

    fun format(csv: CsvTable): String {
        return csv.headers.mapIndexed { index, header ->
            header.ensureLength(csv.columnMaxLengths[index])
        }.joinToString("|") + "\n" +
            csv.headers.mapIndexed { index, _ ->
                "-".times(csv.columnMaxLengths[index])
            }.joinToString("+") + "\n" +
            csv.rowData.mapIndexed { _, row ->
                row.mapIndexed { index, rowEntry ->
                    rowEntry.ensureLength(csv.columnMaxLengths[index])
                }.joinToString("|")
            }.joinToString("\n")
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
