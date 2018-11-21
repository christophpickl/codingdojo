package com.github.christophpickl.codingdojo.csvviewer

interface InputOutput {
    fun print(text: String)
    fun println(text: String)
    fun readLine(): String
}

object SystemInputOutput : InputOutput {

    override fun print(text: String) {
        kotlin.io.print(text)
    }

    override fun println(text: String) {
        kotlin.io.println(text)
    }

    override fun readLine() =
        kotlin.io.readLine() ?: ""

}
