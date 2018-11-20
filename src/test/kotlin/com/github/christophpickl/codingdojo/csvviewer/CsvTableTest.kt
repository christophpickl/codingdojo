package com.github.christophpickl.codingdojo.csvviewer

import org.testng.annotations.Test

@Test
class CsvTableTest {

    fun `When two columns of same row size Then do nothing`() {
        CsvTable(
            headers = listOf("a", "b"),
            rowData = listOf(
                listOf("a1", "b1"),
                listOf("a2", "b2")
            )
        )
    }

    @Test(expectedExceptions = [IllegalArgumentException::class])
    fun `When two columns of different row size Then throw`() {
        CsvTable(
            headers = listOf("a", "b"),
            rowData = listOf(
                listOf("a1", "b1"),
                listOf("a2")
            )
        )
    }

}
