package com.github.christophpickl.codingdojo.csvviewer

import com.github.christophpickl.codingdojo.plusAssign
import com.github.christophpickl.codingdojo.removeFirst

class TestableInputOutput : InputOutput {

    private val _printedText = StringBuilder()
    private val linesToRead = mutableListOf<String>()
    val printedText get() = _printedText.toString()
    private var _fileToRead: List<String>? = null

    override fun print(text: String) {
        _printedText += text
    }

    override fun println(text: String) {
        _printedText += "$text\n"
    }

    override fun readLine() =
        linesToRead.removeFirst()

    fun addLineToRead(line: String) {
        linesToRead += line
    }

    override fun readFile(classpath: String): List<String> =
        _fileToRead ?: throw IllegalStateException("fileToRead was not set")

    fun setFileToRead(fileToRead: List<String>) {
        _fileToRead = fileToRead
    }

}
