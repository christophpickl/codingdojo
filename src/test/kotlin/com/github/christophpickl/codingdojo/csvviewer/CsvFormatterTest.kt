package com.github.christophpickl.codingdojo.csvviewer

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class CsvFormatterTest {

    fun `foo bar`() {
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

}
