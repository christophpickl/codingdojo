package com.github.christophpickl.codingdojo.wordcount

import java.io.File

typealias TextReader = () -> String

fun buildTextReader(args: Array<String>): TextReader? {
    return when (args.size) {
        0 -> askInteractivelyTextReader
        1 -> fromFileTextReader(args[0])
        else -> null
    }
}

val askInteractivelyTextReader = {
    print("Enter text: ")
    readLine() ?: ""
}

fun fromFileTextReader(fileName: String): TextReader {
    val file = File(fileName)
    if (!file.exists()) {
        throw IllegalArgumentException("File does not exist at: ${file.absolutePath}")
    }
    val text = file.readText().replace("\n", " ").trim()
    return { text }
}
