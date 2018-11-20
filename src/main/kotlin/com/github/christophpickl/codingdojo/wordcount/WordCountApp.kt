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

        do {
            val input = readText()
            if (input.isEmpty()) {
                break
            }
            val result = counter.count(input)
            println("Number of words: ${result.wordCount}, unique: ${result.uniqueWordCount}; average word length: ${result.averageLength.format(2)} characters")
            if (indexEnabled) {
                printIndex(dictEnabled, result.index)
            }
        } while (true)
    }

    private fun invalidCliArgs(args: Array<String>) {
        System.out.println("Invalid CLI arguments! (${args.contentToString()})\n" +
            "Expected either none or one argument (filepath) along with:\n" +
            "* an option '-index' argument\n" +
            "* additional to index an option '-dictionary=classpath.txt'")
    }

}

fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)
