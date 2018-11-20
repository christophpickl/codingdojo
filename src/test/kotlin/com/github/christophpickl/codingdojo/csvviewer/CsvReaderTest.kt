package com.github.christophpickl.codingdojo.csvviewer

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class CsvReaderTest {

    private val sampleData = """
        H1;H2
        a1;a2
        b1;b2
    """.trimIndent()

    fun `When read sample data Then return proper structure`() {
        assertThat(CsvReader.read(sampleData),
            equalTo(CsvTable(
                listOf(
                    CsvColumn("H1", listOf("a1", "b1")),
                    CsvColumn("H2", listOf("a2", "b2"))
                )
            )))

    }

}
