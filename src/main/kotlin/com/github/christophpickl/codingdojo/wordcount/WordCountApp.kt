package com.github.christophpickl.codingdojo.wordcount

typealias WordFilter = (String) -> Boolean

object WordCountApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val readText = buildTextReader(args)
        if (readText == null) {
            invalidCliArgs(args)
            return
        }
        val indexEnabled = args.contains(indexCliArg)
        val dictEnabled = isDictEnabled(args)
        val counter = WordCounter(buildStopWordsFilter(), buildDictFilter(args))

        val input = readText()
        val result = counter.count(input)
        // TODO format average length to two digits??
        println("Number of words: ${result.wordCount}, unique: ${result.uniqueWordCount}; average word length: ${result.averageLength} characters")
        if (indexEnabled) {
            printIndex(dictEnabled, result.index)
        }
    }

    private fun invalidCliArgs(args: Array<String>) {
        System.out.println("Invalid CLI arguments! (${args.contentToString()})\n" +
            "Expected either none or one argument (filepath) along with:\n" +
            "* an option '-index' argument\n" +
            "* additional to index an option '-dictionary=classpath.txt'")
    }

}
