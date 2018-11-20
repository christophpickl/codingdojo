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
        val wordCount = counter.count(input)
        println("Number of words: $wordCount")
    }

    private fun invalidCliArgs() {
        System.out.println("Invalid CLI arguments!")
    }
}
