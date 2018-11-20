package com.github.christophpickl.codingdojo.csvviewer

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class TableTest {

    fun `init - When two columns of same row size Then do nothing`() {
        Table(
            headers = listOf("a", "b"),
            rowData = listOf(
                listOf("a1", "b1"),
                listOf("a2", "b2")
            )
        )
    }

    @Test(expectedExceptions = [IllegalArgumentException::class])
    fun `init - When two columns of different row size Then throw`() {
        Table(
            headers = listOf("a", "b"),
            rowData = listOf(
                listOf("a1", "b1"),
                listOf("a2")
            )
        )
    }

    fun `maxLength - When header has max length Then return its length`() {
        assertThat(Table(
            headers = listOf("123", "b"),
            rowData = listOf(listOf("1", "b"))
        ).columnMaxLengths, equalTo(listOf(3, 1)))
    }

    fun `maxLength - When row has max length Then return its length`() {
        assertThat(Table(
            headers = listOf("1", "b"),
            rowData = listOf(listOf("123", "b"))
        ).columnMaxLengths, equalTo(listOf(3, 1)))
    }

}
