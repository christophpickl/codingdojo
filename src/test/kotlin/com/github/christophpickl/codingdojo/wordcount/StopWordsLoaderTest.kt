package com.github.christophpickl.codingdojo.wordcount

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class StopWordsLoaderTest {

    @Test(
        expectedExceptions = [IllegalArgumentException::class],
        expectedExceptionsMessageRegExp = """.*invalid\.txt.*"""
    )
    fun `When load non-existing file Then throw`() {
        StopWordsLoader.load("invalid.txt")
    }

    fun `When load existing file Then return content`() {
        assertThat(StopWordsLoader.load("/test_stopwords.txt"),
            equalTo(listOf("a", "b", "c")))
    }

}
