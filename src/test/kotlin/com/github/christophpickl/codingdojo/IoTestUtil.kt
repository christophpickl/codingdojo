package com.github.christophpickl.codingdojo

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

// https://github.com/christophpickl/kpotpourri/blob/master/common4k/src/main/kotlin/com/github/christophpickl/kpotpourri/common/io/io.kt
object IoTestUtil {

    @Suppress("MemberVisibilityCanBePrivate")
    fun readFrom(actionWhichWritesToStdOut: () -> Unit): String {
        val old = System.out
        val byteStream = ByteArrayOutputStream()
        val stream = PrintStream(byteStream)
        try {
            System.setOut(stream)
            actionWhichWritesToStdOut()
            System.out.flush()
        } finally {
            System.setOut(old)
        }
        val printed = byteStream.toString()
        stream.close()
        return printed
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun writeTo(text: String, actionWhichReadsFromStdIn: () -> Unit) {
        val old = System.`in`
        try {
            System.setIn(ByteArrayInputStream(text.toByteArray()))
            actionWhichReadsFromStdIn()
        } finally {
            System.setIn(old)
        }
    }

    fun readAndWrite(text: String, action: () -> Unit): String =
        readFrom {
            writeTo(text) {
                action()
            }
        }

}
