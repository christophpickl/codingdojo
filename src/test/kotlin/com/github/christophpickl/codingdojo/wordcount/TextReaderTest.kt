package com.github.christophpickl.codingdojo.wordcount

import com.github.christophpickl.codingdojo.IoTestUtil
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class TextReaderTest {

    fun `askInteractivelyTextReader - When enter some text Then return that entered text`() {
        val someText = "foo bar"
        IoTestUtil.writeTo(someText) {
            assertThat(askInteractivelyTextReader(),
                equalTo(someText))
        }
    }

    @Test(
        expectedExceptions = [IllegalArgumentException::class],
        expectedExceptionsMessageRegExp = """.*invalid\.txt.*"""
    )
    fun `fromFileTextReader - When read invalid file Then throw`() {
        fromFileTextReader("invalid.txt")()
    }

    fun `fromFileTextReader - When read valid file as given in requirements Then return text content without linebreaks`() {
        assertThat(fromFileTextReader("src/test/resources/test_readTextFromThisFile.txt")(),
            equalTo("Mary had a little lamb"))
    }
}
