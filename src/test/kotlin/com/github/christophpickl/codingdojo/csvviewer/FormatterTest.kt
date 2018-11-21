package com.github.christophpickl.codingdojo.csvviewer

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class FormatterTest {

    private val twoRowedAndtwoColumnedTable = Table(
        headers = listOf("H1", "H2"),
        rowData = listOf(
            listOf("a1", "a2"),
            listOf("b1", "b2")
        )
    )

    fun `When format table without whitespaces Then return proper string`() {
        assertThat(Formatter.format(twoRowedAndtwoColumnedTable), equalTo("""
            H1|H2
            --+--
            a1|a2
            b1|b2
        """.trimIndent()))
    }

    fun `When header has over length Then indent rows of first column`() {
        assertThat(Formatter.format(Table(
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
        assertThat(Formatter.format(Table(
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

    fun `When format table with 2 rows but page of skip 0 and take 1 Then return first row`() {
        assertThat(Formatter.format(twoRowedAndtwoColumnedTable, PageRequest(skip = 0, take = 1)), equalTo("""
            H1|H2
            --+--
            a1|a2
        """.trimIndent()))
    }

    fun `When format table with 2 rows but page of skip 1 and take 1 Then return first row`() {
        assertThat(Formatter.format(twoRowedAndtwoColumnedTable, PageRequest(skip = 1, take = 1)), equalTo("""
            H1|H2
            --+--
            b1|b2
        """.trimIndent()))
    }
    
}
