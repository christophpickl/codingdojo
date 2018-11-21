package com.github.christophpickl.codingdojo.csvviewer

import com.github.christophpickl.codingdojo.plusAssign
import com.github.christophpickl.codingdojo.removeFirst

class TestableInputOutput : InputOutput {

    private val _printedText = StringBuilder()
    private val linesToRead = mutableListOf<String>()
    val printedText get() = _printedText.toString()

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

}
