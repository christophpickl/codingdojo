package com.github.christophpickl.codingdojo.wordcount

import java.util.regex.Pattern

const val indexCliArg = "-index"

class WordCounter(
    private val stopWordsFilter: WordFilter,
    private val dictionaryFilter: WordFilter
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
            lazyIndex = lazy {
                Index(
                    words
                        .distinct()
                        .sortedWith(String.CASE_INSENSITIVE_ORDER)
                        .map { IndexEntry(it, dictionaryFilter(it)) }
                )
            }
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
    lazyIndex: Lazy<Index>
) {
    val index: Index by lazyIndex

    companion object {
        val empty = CountResult(0, 0, 0.0, lazy { Index.empty })
    }
}

class Index(
    val words: List<IndexEntry>
) {
    val unknownWordsCount = words.filter { !it.knownByDictionary }.count()

    companion object {
        val empty = Index(emptyList())
    }
}

class IndexEntry(
    val term: String,
    val knownByDictionary: Boolean
)
