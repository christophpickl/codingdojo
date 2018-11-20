package com.github.christophpickl.codingdojo.wordcount

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class ClasspathFileLoaderTest {

    @Test(
        expectedExceptions = [IllegalArgumentException::class],
        expectedExceptionsMessageRegExp = """.*invalid\.txt.*"""
    )
    fun `When readLinesOf non-existing file Then throw`() {
        ClasspathFileLoader.readLinesOf("invalid.txt")
    }

    fun `When readLinesOf existing file Then return content`() {
        assertThat(ClasspathFileLoader.readLinesOf("test_stopwords.txt"),
            equalTo(listOf("a", "b", "c")))
    }

}
