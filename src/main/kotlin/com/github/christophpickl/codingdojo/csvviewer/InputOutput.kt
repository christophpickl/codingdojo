package com.github.christophpickl.codingdojo.csvviewer

interface InputOutput {
    fun print(any: Any)
    fun println(any: Any)
    fun readLine(): String
}

object SystemInputOutput : InputOutput {
    
    override fun print(any: Any) {
        kotlin.io.print(any)
    }

    override fun println(any: Any) {
        kotlin.io.println(any)
    }

    override fun readLine() =
        kotlin.io.readLine() ?: ""

}
