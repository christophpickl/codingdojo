package com.github.christophpickl.codingdojo

operator fun StringBuilder.plusAssign(text: String) {
    append(text)
}

fun <E> MutableList<E>.removeFirst() = removeAt(0)

fun <T> doUntilNotNull(action: () -> T?): T {
    var result: T?
    do {
        result = action()
    } while (result == null)
    return result
}
