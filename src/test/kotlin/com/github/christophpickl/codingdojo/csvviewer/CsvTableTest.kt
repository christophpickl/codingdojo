package com.github.christophpickl.codingdojo.csvviewer

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class CsvTableTest {

    fun `init - When two columns of same row size Then do nothing`() {
        CsvTable(
            headers = listOf("a", "b"),
            rowData = listOf(
                listOf("a1", "b1"),
                listOf("a2", "b2")
            )
        )
    }

    @Test(expectedExceptions = [IllegalArgumentException::class])
    fun `init - When two columns of different row size Then throw`() {
        CsvTable(
            headers = listOf("a", "b"),
            rowData = listOf(
                listOf("a1", "b1"),
                listOf("a2")
            )
        )
    }

    fun `maxLength - When header has max length Then return its length`() {
        assertThat(CsvTable(
            headers = listOf("123"),
            rowData = listOf(listOf("1"))
        ).columnMaxLengths, equalTo(listOf(3)))
    }

    fun `maxLength - When row has max length Then return its length`() {
        assertThat(CsvTable(
            headers = listOf("1"),
            rowData = listOf(listOf("123"))
        ).columnMaxLengths, equalTo(listOf(3)))
    }
}
