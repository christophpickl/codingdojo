package com.github.christophpickl.codingdojo.csvviewer

import java.io.File

object CsvViewerApp {

    const val invalidArgsMessage = "Must define filename as application argument!"

    @JvmStatic
    fun main(args: Array<String>) {
        if (args.size != 1 && args.size != 2) {
            println(invalidArgsMessage)
            return
        }
        val pageSize = if (args.size >= 2) args[1].toInt() else CsvViewer.DEFAULT_PAGE_SIZE
        val table = Reader.read(readLinesOf(args[0]))
        val viewer = CsvViewer(table, pageSize)
        viewer.view()
    }

    private fun readLinesOf(classpath: String): List<String> {
        val resource = javaClass.getResource("/csvviewer/$classpath")
            ?: throw IllegalArgumentException("File does not exist at classpath: $classpath")
        return File(resource.toURI()).readLines()
    }

}
