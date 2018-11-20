package com.github.christophpickl.codingdojo.wordcount

object WordCountApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val readText = buildTextReader(args)
        if (readText == null) {
            invalidCliArgs()
            return
        }

        val counter = WordCounter(buildStopWordsFilter())
        val input = readText()
        val (wordCount, uniqueWordCount) = counter.count(input)
        println("Number of words: $wordCount, unique: $uniqueWordCount")
    }

    private fun invalidCliArgs() {
        System.out.println("Invalid CLI arguments!")
    }
}
