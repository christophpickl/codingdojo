package com.github.christophpickl.codingdojo.wordcount

import java.util.regex.Pattern

class WordCounter(
    private val stopWordsFilter: StopWordsFilter
) {

    private val multiWhitespacePattern = Pattern.compile("\\s+")
    private val wordPattern = Pattern.compile("""[a-zA-Z]+""")

    fun count(text: String): CountResult {
        if (text.isEmpty() || text.isBlank()) {
            return CountResult.empty
        }

        val words = multiWhitespacePattern.matcher(text).replaceAll(" ")
            .split(" ", "-")
            .map { it.removeSuffix(".") }
            .filter { wordPattern.matcher(it).matches() }
            .filterNot(stopWordsFilter)

        return CountResult(
            wordCount = words.size,
            uniqueWordCount = words.distinct().size
        )
    }

}

data class CountResult(
    val wordCount: Int,
    val uniqueWordCount: Int
) {
    companion object {
        val empty = CountResult(0, 0)
    }
}
