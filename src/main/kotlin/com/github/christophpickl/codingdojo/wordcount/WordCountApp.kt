package com.github.christophpickl.codingdojo.wordcount

object WordCountApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val readText = buildTextReader(args)
        if (readText == null) {
            invalidCliArgs(args)
            return
        }
        val indexEnabled = args.contains("-index")
        val counter = WordCounter(buildStopWordsFilter())
        val input = readText()
        val result = counter.count(input)
        println("Number of words: ${result.wordCount}, unique: ${result.uniqueWordCount}; average word length: ${result.averageLength} characters")
        if (indexEnabled) {
            println("Index:")
            result.index.forEach(::println)
        }
    }

    private fun invalidCliArgs(args: Array<String>) {
        System.out.println("Invalid CLI arguments! (${args.contentToString()})\n" +
            "Expected either none or one argument (filepath) along with an option '-index' argument.")
    }

}
