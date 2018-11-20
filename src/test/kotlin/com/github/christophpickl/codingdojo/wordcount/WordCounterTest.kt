package com.github.christophpickl.codingdojo.wordcount

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class WordCounterTest {

    fun `When counting empty string Then return 0`() {
        assertThat(WordCounter().count(""), equalTo(0))
    }

    fun `When counting single word Then return 1`() {
        assertThat(WordCounter().count("a"), equalTo(1))
    }

    fun `When counting two words separated by space Then return 2`() {
        assertThat(WordCounter().count("a b"), equalTo(2))
    }

}
