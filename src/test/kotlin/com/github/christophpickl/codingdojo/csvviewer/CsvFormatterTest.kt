package com.github.christophpickl.codingdojo.csvviewer

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class CsvFormatterTest {

    fun `When format table without whitespaces Then return proper string`() {
        assertThat(CsvFormatter.format(CsvTable(
            headers = listOf("H1", "H2"),
            rowData = listOf(
                listOf("a1", "a2"),
                listOf("b1", "b2")
            )
        )), equalTo("""
            H1|H2
            --+--
            a1|a2
            b1|b2
        """.trimIndent()))
    }

    fun `When header has over length Then indent rows of first column`() {
        assertThat(CsvFormatter.format(CsvTable(
            headers = listOf("H1a", "H2"),
            rowData = listOf(
                listOf("a1", "a2"),
                listOf("b1", "b2")
            )
        )), equalTo("""
            H1a|H2
            ---+--
            a1 |a2
            b1 |b2
        """.trimIndent()))
    }

    fun `When row has over length Then indent rows of first column including header`() {
        assertThat(CsvFormatter.format(CsvTable(
            headers = listOf("H1", "H2"),
            rowData = listOf(
                listOf("a1a", "a2"),
                listOf("b1", "b2")
            )
        )), equalTo("""
            H1 |H2
            ---+--
            a1a|a2
            b1 |b2
        """.trimIndent()))
    }

}
