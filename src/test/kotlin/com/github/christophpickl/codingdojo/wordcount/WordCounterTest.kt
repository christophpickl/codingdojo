package com.github.christophpickl.codingdojo.wordcount

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class WordCounterTest {

    private val neverIsStopWord: StopWordsFilter = { false }

    fun `When counting empty string Then return 0`() {
        assertThat(counter().count(""), equalTo(0))
    }

    fun `When counting whitespace Then return 0`() {
        assertThat(counter().count(" "), equalTo(0))
    }

    fun `When counting single word Then return 1`() {
        assertThat(counter().count("a"), equalTo(1))
    }

    fun `When counting single multi lettered word Then return 1`() {
        assertThat(counter().count("aaa"), equalTo(1))
    }

    fun `When counting single number Then return 0`() {
        assertThat(counter().count("1"), equalTo(0))
    }

    fun `When counting single non-word Then return 0`() {
        assertThat(counter().count("a1a"), equalTo(0))
    }

    fun `When counting two words separated by space Then return 2`() {
        assertThat(counter().count("a b"), equalTo(2))
    }

    fun `When counting two words separated by two spaces Then return 2`() {
        assertThat(counter().count("a  b"), equalTo(2))
    }

    fun `When counting sample sentence from requirements Then return proper word count`() {
        assertThat(counter().count("Mary had a little lamb"), equalTo(5))
    }

    private fun counter() = WordCounter(neverIsStopWord)

}
