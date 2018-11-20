package com.github.christophpickl.codingdojo.wordcount

const val indexCliArg = "-index"
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

fun printIndex(dictEnabled: Boolean, index: Index) {
    println("Index${if (dictEnabled) " (unknown: ${index.unknownWordsCount})" else ""}:")
    index.words.forEach {
        println("${it.term}${if (dictEnabled && !it.knownByDictionary) "*" else ""}")
    }
}

class Index(
    val words: List<IndexEntry>
) {
    val unknownWordsCount = words.filter { !it.knownByDictionary }.count()

    companion object {
        val empty = Index(emptyList())

        fun build(words: List<String>, dictionaryFilter: WordFilter) =
            Index(words.distinct().sortedWith(String.CASE_INSENSITIVE_ORDER).map { IndexEntry(it, dictionaryFilter(it)) })
    }
}

data class IndexEntry(
    val term: String,
    val knownByDictionary: Boolean
)
