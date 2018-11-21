package com.github.christophpickl.codingdojo

import com.github.christophpickl.codingdojo.csvviewer.logic.Table

val Table.Companion.tableOneColumnTwoRows get() = Table(listOf("a"), listOf(listOf("1"), listOf("2")))

@Suppress("UNCHECKED_CAST")
inline fun <reified T> List<T>.toDataProviding() =
    map { arrayOf(it) }.toTypedArray() as Array<Array<out Any?>>
