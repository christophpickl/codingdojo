package com.github.christophpickl.codingdojo.csvviewer.cli

import java.io.File

interface InputOutput {
    fun print(text: String)
    fun println(text: String)
    fun readLine(): String
    fun readFile(classpath: String): List<String>
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

    override fun readFile(classpath: String): List<String> {
        val resource = javaClass.getResource(classpath)
            ?: throw IllegalArgumentException("File does not exist at classpath: $classpath")
        return File(resource.toURI()).readLines()
    }

}
