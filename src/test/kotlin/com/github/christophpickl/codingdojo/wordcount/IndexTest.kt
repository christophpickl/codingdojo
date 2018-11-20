package com.github.christophpickl.codingdojo.wordcount

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.sameInstance
import org.testng.annotations.Test

@Test
class IndexTest {

    fun `When build filter with empty array Then return disabled dict instance`() {
        assertThat(buildDictFilter(emptyArray()),
            sameInstance(disabledDict))
    }

    @Test(
        expectedExceptions = [IllegalArgumentException::class],
        expectedExceptionsMessageRegExp = """.*invalid\.txt.*"""
    )
    fun `When build filter with invalid dictionary file Then throw`() {
        buildDictFilter(arrayOf("-dictionary=invalid.txt"))
    }

    fun `When build filter with valid dictionary file Then return proper filter`() {
        val filter = buildDictFilter(arrayOf("-dictionary=test_dict.txt"))

        assertThat(filter("big"), equalTo(true))
        assertThat(filter("bigg"), equalTo(false))
    }

    @Suppress("MoveLambdaOutsideParentheses")
    fun `When build index Then return proper instance`() {
        val actual = Index.build(listOf("B", "a"), { word: String -> word == "a" })
        assertThat(actual.unknownWordsCount, equalTo(1))
        assertThat(actual.words, equalTo(listOf(
            IndexEntry("a", true),
            IndexEntry("B", false)
        )))
    }

}
