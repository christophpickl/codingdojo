package com.github.christophpickl.codingdojo.wordcount

import java.util.regex.Pattern

class WordCounter(
    private val stopWordsFilter: StopWordsFilter
) {

    private val multiWhitespacePattern = Pattern.compile("\\s+")
    private val wordPattern = Pattern.compile("""[a-zA-Z-]+""")

    fun count(text: String): CountResult {
        if (text.isEmpty() || text.isBlank()) {
            return CountResult.empty
        }
        val words = splitText(text)
        return CountResult(
            wordCount = words.size,
            uniqueWordCount = words.distinct().size,
            averageLength = words.map { it.length }.average(),
            lazyIndex = lazy { words.distinct().sortedWith(String.CASE_INSENSITIVE_ORDER) }
        )
    }

    private fun splitText(text: String): List<String> {
        return multiWhitespacePattern.matcher(text).replaceAll(" ")
            .split(" ")
            .map { it.removeSuffix(".") }
            .filter { wordPattern.matcher(it).matches() }
            .filterNot(stopWordsFilter)
    }

}

class CountResult(
    val wordCount: Int,
    val uniqueWordCount: Int,
    val averageLength: Double,
    lazyIndex: Lazy<List<String>>
) {
    val index: List<String> by lazyIndex

    companion object {
        val empty = CountResult(0, 0, 0.0, lazy { emptyList<String>() })
    }
}
