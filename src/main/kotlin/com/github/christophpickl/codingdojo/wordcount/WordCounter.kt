package com.github.christophpickl.codingdojo.wordcount

import java.util.regex.Pattern

class WordCounter {

    private val multiWhitespace = Pattern.compile("\\s+")

    fun count(text: String): Int {
        if (text.isEmpty()) {
            return 0
        }
        val cleanedText = multiWhitespace.matcher(text).replaceAll(" ")
        return cleanedText.split(" ").size
    }
}
