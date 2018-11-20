package com.github.christophpickl.codingdojo.wordcount

import java.io.File

typealias StopWordsFilter = (String) -> Boolean

private const val stopWordsClasspath = "/stopwords.txt"

fun buildStopWordsFilter(): StopWordsFilter {
    val stopWords = StopWordsLoader.load(stopWordsClasspath)
    return { word: String -> stopWords.contains(word) }
}

object StopWordsLoader {
    fun load(classpath: String): List<String> {
        val resource = javaClass.getResource(classpath)
            ?: throw IllegalArgumentException("File does not exist at classpath: $classpath")
        return File(resource.toURI()).readLines()
    }
}
