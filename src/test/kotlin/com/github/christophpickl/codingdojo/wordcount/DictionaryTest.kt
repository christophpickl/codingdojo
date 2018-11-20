package com.github.christophpickl.codingdojo.wordcount

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.sameInstance
import org.testng.annotations.Test

@Test
class DictionaryTest {

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

}
