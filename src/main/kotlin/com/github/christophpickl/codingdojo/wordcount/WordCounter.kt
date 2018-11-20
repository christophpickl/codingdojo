package com.github.christophpickl.codingdojo.wordcount

import java.util.regex.Pattern

class WordCounter(
    private val stopWordsFilter: StopWordsFilter
) {

    private val multiWhitespacePattern = Pattern.compile("\\s+")
    private val wordPattern = Pattern.compile("[a-zA-Z]+")

    fun count(text: String): Int {
        if (text.isEmpty() || text.isBlank()) {
            return 0
        }
        return multiWhitespacePattern.matcher(text).replaceAll(" ")
            .split(" ")
            .filter { wordPattern.matcher(it).matches() }
            .filterNot(stopWordsFilter)
            .size
    }

}
