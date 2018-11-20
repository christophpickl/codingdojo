package com.github.christophpickl.codingdojo.wordcount

import java.io.File

typealias TextReader = () -> String

val askInteractivelyTextReader = {
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
