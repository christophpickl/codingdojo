package com.github.christophpickl.codingdojo.csvviewer

import com.github.christophpickl.codingdojo.csvviewer.cli.Args
import com.github.christophpickl.codingdojo.csvviewer.cli.ArgsParser
import com.github.christophpickl.codingdojo.csvviewer.cli.Engine
import com.github.christophpickl.codingdojo.csvviewer.cli.SystemInputOutput
import com.github.christophpickl.codingdojo.csvviewer.logic.Reader

object CsvViewerApp {

    private const val classpathDirectory = "/csvviewer"
    private val io = SystemInputOutput

    @JvmStatic
    fun main(cliArgs: Array<String>) {
        when (val args = ArgsParser.parse(cliArgs)) {
            is Args.RightArgs -> {
                try {
                    start(args)
                } catch (e: Exception) {
                    io.println("[EXCEPTION] ${e.message ?: "N/A"}")
                }
            }
            is Args.WrongArgs -> {
                io.println(args.message)
            }
        }
    }

    private fun start(args: Args.RightArgs) {
        val table = Reader.read(io.readFile("$classpathDirectory/${args.csvFile}"))
        val engine = Engine.build(table = table, pageSize = args.pageSize)
        engine.startCommandLoop()
    }

}
