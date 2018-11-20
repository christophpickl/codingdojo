package com.github.christophpickl.codingdojo.wordcount

import java.util.regex.Pattern

class WordCounter {

    private val multiWhitespacePattern = Pattern.compile("\\s+")
    private val wordPattern = Pattern.compile("[a-zA-Z]")

    fun count(text: String): Int {
        if (text.isEmpty() || text.isBlank()) {
            return 0
        }
        val cleanedText = multiWhitespacePattern.matcher(text).replaceAll(" ")
        return cleanedText.split(" ").filter { wordPattern.matcher(it).matches() }.size
    }

}
