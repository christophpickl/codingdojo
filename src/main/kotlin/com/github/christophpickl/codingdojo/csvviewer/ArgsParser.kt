package com.github.christophpickl.codingdojo.csvviewer

object ArgsParser {

    private const val defaultPageSize = 10

    fun parse(cliArgs: Array<String>): Args {
        if (cliArgs.size != 1 && cliArgs.size != 2) {
            return Args.WrongArgs(cliArgs, "Number of arguments must be either 1 or 2 but was: ${cliArgs.size}")
        }
        return Args.RightArgs(
            csvFile = cliArgs[0],
            pageSize = if (cliArgs.size >= 2)
                cliArgs[1].toIntOrNull() ?: return Args.WrongArgs(cliArgs, "Page size must be a valid number!")
            else defaultPageSize
        )
    }

}

sealed class Args {

    class WrongArgs(
        cliArgs: Array<String>,
        internalMessage: String
    ) : Args() {
        val message = "[ERROR] $internalMessage (CLI args: '${cliArgs.joinToString()}')"
    }

    data class RightArgs(
        val csvFile: String,
        val pageSize: Int
    ) : Args()
    
}
