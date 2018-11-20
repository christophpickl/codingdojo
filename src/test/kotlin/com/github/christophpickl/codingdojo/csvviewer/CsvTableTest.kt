package com.github.christophpickl.codingdojo.csvviewer

import org.testng.annotations.Test

@Test
class CsvTableTest {

    fun `When two columns of same row size Then do nothing`() {
        CsvTable(listOf(
            CsvColumn("a", listOf("x")),
            CsvColumn("b", listOf("y"))
        ))
    }

    @Test(expectedExceptions = [IllegalArgumentException::class])
    fun `When two columns of different row size Then throw`() {
        CsvTable(listOf(
            CsvColumn("a", listOf("x")),
            CsvColumn("b", listOf("y", "y2"))
        ))
    }

}
