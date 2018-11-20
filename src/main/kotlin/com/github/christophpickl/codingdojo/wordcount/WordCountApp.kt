package com.github.christophpickl.codingdojo.wordcount

import java.text.DecimalFormat

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

        WordCountStarter(indexEnabled, dictEnabled, counter, readText).start(
            loopEnabled = (readText === askInteractivelyTextReader)
        )
    }

    private fun invalidCliArgs(args: Array<String>) {
        System.out.println("Invalid CLI arguments! (${args.contentToString()})\n" +
            "Expected either none or one argument (filepath) along with:\n" +
            "* an option '-index' argument\n" +
            "* additional to index an option '-dictionary=classpath.txt'")
    }


}

class WordCountStarter(
    private val indexEnabled: Boolean,
    private val dictEnabled: Boolean,
    private val counter: WordCounter,
    private val readText: TextReader
) {

    private val doubleFormat = DecimalFormat("0.0#")

    fun start(loopEnabled: Boolean) {
        if (loopEnabled) {
            var continueLoop: Boolean
            do {
                continueLoop = readWordsAndPrintResult()
            } while (continueLoop)
        } else {
            readWordsAndPrintResult()
        }
    }

    private fun readWordsAndPrintResult(): Boolean {
        val input = readText()
        if (input.isEmpty()) {
            return false
        }
        val result = counter.count(input)
        println("Number of words: ${result.wordCount}, unique: ${result.uniqueWordCount}; average word length: ${result.averageLength.format()} characters")
        if (indexEnabled) {
            printIndex(dictEnabled, result.index)
        }
        return true
    }

    private fun Double.format() = doubleFormat.format(this)

}
