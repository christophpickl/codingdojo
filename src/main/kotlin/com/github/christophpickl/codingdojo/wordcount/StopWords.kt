package com.github.christophpickl.codingdojo.wordcount

import java.io.File

class StopWordsFilter(
    private val stopWords: List<String>
) {
    fun isStopWord(word: String) = stopWords.contains(word)
}

object StopWordsLoader {
    fun load(classpath: String): List<String> {
        val resource = javaClass.getResource(classpath)
            ?: throw IllegalArgumentException("File does not exist at classpath: $classpath")
        return File(resource.toURI()).readLines()
    }
}
