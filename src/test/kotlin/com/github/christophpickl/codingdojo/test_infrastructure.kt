package com.github.christophpickl.codingdojo

@Suppress("UNCHECKED_CAST")
inline fun <reified T> List<T>.toDataProviding() =
    map { arrayOf(it) }.toTypedArray() as Array<Array<out Any?>>
