package com.github.christophpickl.codingdojo.wordcount

private const val stopWordsClasspath = "stopwords.txt"

fun buildStopWordsFilter(): WordFilter {
    val stopWords = ClasspathFileLoader.readLinesOf(stopWordsClasspath)
    return { word: String -> stopWords.contains(word) }
}
