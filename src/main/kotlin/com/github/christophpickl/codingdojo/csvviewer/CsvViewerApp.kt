package com.github.christophpickl.codingdojo.csvviewer

object CsvViewerApp {
    @JvmStatic
    fun main(args: Array<String>) {
        if (args.size != 1) {
            println("Must define filename as application argument!")
            return
        }
    }
}
