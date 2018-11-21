package com.github.christophpickl.codingdojo.csvviewer.logic

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test(groups = ["csvviewer"])
class ReaderTest {

    fun `When read empty CSV Then return empty table`() {
        assertThat(Reader.read(emptyList()),
            equalTo(Table.empty))
    }

    fun `When read some CSV Then return proper structure`() {
        assertThat(Reader.read(listOf(
            "H1;H2",
            "a1;a2",
            "b1;b2"
        )),
            equalTo(Table(
                headers = listOf("H1", "H2"),
                rowData = listOf(
                    listOf("a1", "a2"),
                    listOf("b1", "b2")
                )
            )))
    }

    fun `When read CSV with blank line Then skip blank line`() {
        assertThat(Reader.read(listOf(
            "H1;H2",
            "a1;a2",
            ""
        )),
            equalTo(Table(
                headers = listOf("H1", "H2"),
                rowData = listOf(
                    listOf("a1", "a2")
                )
            )))
    }

}
