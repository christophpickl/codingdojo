package com.github.christophpickl.codingdojo.wordcount

object WordCountApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val readText = buildTextReader(args)
        if (readText == null) {
            invalidCliArgs(args)
            return
        }

        val counter = WordCounter(buildStopWordsFilter())
        val input = readText()
        val (wordCount, uniqueWordCount) = counter.count(input)
        println("Number of words: $wordCount, unique: $uniqueWordCount")
    }

    private fun invalidCliArgs(args: Array<String>) {
        System.out.println("Invalid CLI arguments! (${args.contentToString()})\nExpected either none or one argument (filepath)")
    }

}
