package com.github.christophpickl.codingdojo.csvviewer

object CsvViewerApp {

    private val io = SystemInputOutput

    @JvmStatic
    fun main(cliArgs: Array<String>) {
        when (val args = ArgsParser.parse(cliArgs)) {
            is Args.RightArgs -> {
                try {
                    CsvViewerStarter(io = io).start(args)
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
        val table = Reader.read(io.readFile("/csvviewer/${args.csvFile}"))
        val viewer = CsvViewer(table = table, pageSize = args.pageSize)
        viewer.startCommandLoop()
    }

}
