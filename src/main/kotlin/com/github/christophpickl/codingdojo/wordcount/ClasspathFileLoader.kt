package com.github.christophpickl.codingdojo.wordcount

import java.io.File

object ClasspathFileLoader {
    fun readLinesOf(classpath: String): List<String> {
        val resource = javaClass.getResource("/wordcount/$classpath")
            ?: throw IllegalArgumentException("File does not exist at classpath: $classpath")
        return File(resource.toURI()).readLines()
    }
}
