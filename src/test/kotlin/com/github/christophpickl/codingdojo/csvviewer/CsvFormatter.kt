package com.github.christophpickl.codingdojo.csvviewer

object CsvFormatter {

    fun format(csv: CsvTable): String =
        csv.headers.joinToString("|") + "\n" +
            "--+--\n" +
            csv.rowData.joinToString("\n") { it.joinToString("|") }

}
