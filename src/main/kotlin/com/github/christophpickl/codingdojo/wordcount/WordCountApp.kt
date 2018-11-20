package com.github.christophpickl.codingdojo.wordcount

object WordCountApp {
    @JvmStatic
    fun main(args: Array<String>) {
        print("Enter text: ")
        val input = readLine() ?: ""
        val wordCount = WordCounter().count(input)
        println("Number of words: $wordCount")
    }
}
