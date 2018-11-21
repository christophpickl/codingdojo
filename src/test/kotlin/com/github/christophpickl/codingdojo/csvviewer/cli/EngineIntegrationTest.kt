package com.github.christophpickl.codingdojo.csvviewer.cli

import com.github.christophpickl.codingdojo.IoUtil
import com.github.christophpickl.codingdojo.csvviewer.logic.Table
import com.natpryce.hamkrest.allOf
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.containsSubstring
import org.testng.annotations.Test

@Test(groups = ["csvviewer"])
class EngineIntegrationTest {

    private val tableOneColumnTwoRows = Table(listOf("a"), listOf(listOf("1"), listOf("2")))
    // TODO rewrite with I/O
    fun `Given page size equals 1 When choose exit Then print table with one row`() {
        val printed = IoUtil.readAndWrite("x\n") {
            Engine(table = tableOneColumnTwoRows, pageSize = 1).startCommandLoop()
        }
        assertThat(printed, containsSubstring("""
            No.|a
            ---+-
            1. |1
        """.trimIndent()))
    }

    fun `Given page size equals 1 When choose next and exit Then print two proper tables`() {
        val printed = IoUtil.readAndWrite("n\nx\n") {
            Engine(table = tableOneColumnTwoRows, pageSize = 1).startCommandLoop()
        }
        assertThat(printed, allOf(
            containsSubstring("""
            No.|a
            ---+-
            1. |1
        """.trimIndent()),
            containsSubstring("""
            No.|a
            ---+-
            2. |2
        """.trimIndent())
        ))
    }

}
