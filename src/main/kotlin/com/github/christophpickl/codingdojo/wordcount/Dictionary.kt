package com.github.christophpickl.codingdojo.wordcount

const val dictionaryCliArg = "-dictionary="
val disabledDict: WordFilter = { true }

fun isDictEnabled(args: Array<String>): Boolean = findDictArg(args) != null

fun buildDictFilter(args: Array<String>): WordFilter {
    val arg = findDictArg(args) ?: return disabledDict
    val argValue = arg.substring(dictionaryCliArg.length)
    val lines = ClasspathFileLoader.readLinesOf(argValue)
    return { word: String -> lines.contains(word) }
}

private fun findDictArg(args: Array<String>) = args.firstOrNull { it.startsWith(dictionaryCliArg) }
