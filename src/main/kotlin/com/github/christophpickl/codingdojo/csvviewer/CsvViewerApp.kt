package com.github.christophpickl.codingdojo.csvviewer

import java.io.File

object CsvViewerApp {

    // TODO io testable => outsource separate class
    private val io = SystemInputOutput

    @JvmStatic
    fun main(cliArgs: Array<String>) {
        val starter = CsvViewerStarter(io = io)
        when (val args = ArgsParser.parse(cliArgs)) {
            is Args.RightArgs -> {
                try {
                    starter.start(args)
                } catch (e: Exception) {
                    io.println("[EXCEPTION] ${e.message ?: "N/A"}")
                }
            }
            is Args.WrongArgs -> {
                io.println(args.message)
            }
        }
    }
}

class CsvViewerStarter(
    private val io: InputOutput = SystemInputOutput
) {

    fun start(args: Args.RightArgs) {
        val table = Reader.read(readLinesOf(args.csvFile))
        val viewer = CsvViewer(table = table, pageSize = args.pageSize)
        viewer.startCommandLoop()
    }

    // TODO outsource (via IO)
    private fun readLinesOf(classpath: String): List<String> {
        val resource = javaClass.getResource("/csvviewer/$classpath")
            ?: throw IllegalArgumentException("File does not exist at classpath: $classpath")
        return File(resource.toURI()).readLines()
    }

}
