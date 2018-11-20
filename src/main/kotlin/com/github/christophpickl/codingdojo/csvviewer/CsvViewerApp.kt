package com.github.christophpickl.codingdojo.csvviewer

import java.io.File

object CsvViewerApp {

    @JvmStatic
    fun main(args: Array<String>) {
        if (args.size != 1) {
            println("Must define filename as application argument!")
            return
        }
        val csv = Reader.read(readLinesOf(args[0]))
        println(Formatter.format(csv))
    }

    private fun readLinesOf(classpath: String): List<String> {
        val resource = javaClass.getResource("/csvviewer/$classpath")
            ?: throw IllegalArgumentException("File does not exist at classpath: $classpath")
        return File(resource.toURI()).readLines()
    }
    
}
