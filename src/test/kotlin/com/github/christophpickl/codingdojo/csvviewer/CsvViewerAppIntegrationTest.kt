package com.github.christophpickl.codingdojo.csvviewer

import com.github.christophpickl.codingdojo.IoTestUtil
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class CsvViewerAppIntegrationTest {

    fun `When passing empty cli args Then print error message`() {
        val printed = IoTestUtil.readFrom {
            CsvViewerApp.main(emptyArray())
        }
        assertThat(printed, equalTo("Must define filename as application argument!\n"))
    }

    @Test(
        expectedExceptions = [IllegalArgumentException::class],
        expectedExceptionsMessageRegExp = """.*invalid\.csv.*"""
    )
    fun `When passing invalid file path Then throw`() {
        CsvViewerApp.main(arrayOf("invalid.csv"))
    }

}
