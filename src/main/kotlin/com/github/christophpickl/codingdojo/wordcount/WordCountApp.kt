package com.github.christophpickl.codingdojo.wordcount

object WordCountApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val counter = WordCounter(buildStopWordsFilter())
        print("Enter text: ")
        val input = readLine() ?: ""
        val wordCount = counter.count(input)
        println("Number of words: $wordCount")
    }

}
