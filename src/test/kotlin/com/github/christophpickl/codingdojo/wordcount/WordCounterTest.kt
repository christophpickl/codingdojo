package com.github.christophpickl.codingdojo.wordcount

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class WordCounterTest {

    private val neverIsStopWord: StopWordsFilter = { false }

    fun `When counting empty string Then word count is 0`() {
        assertThat(counter().count("").wordCount, equalTo(0))
    }

    fun `When counting whitespace Then word count is 0`() {
        assertThat(counter().count(" ").wordCount, equalTo(0))
    }

    fun `When counting single word Then word count is 1`() {
        assertThat(counter().count("a").wordCount, equalTo(1))
    }

    fun `When counting single multi lettered word Then word count is 1`() {
        assertThat(counter().count("aaa").wordCount, equalTo(1))
    }

    fun `When counting single number Then word count is 0`() {
        assertThat(counter().count("1").wordCount, equalTo(0))
    }

    fun `When counting single non-word Then word count is 0`() {
        assertThat(counter().count("a1a").wordCount, equalTo(0))
    }

    fun `When counting two words separated by space Then word count is 2`() {
        assertThat(counter().count("a a").wordCount, equalTo(2))
    }

    fun `When counting two words separated by two spaces Then word count is 2`() {
        assertThat(counter().count("a  a").wordCount, equalTo(2))
    }

    fun `When counting sample sentence from requirements Then return proper word count`() {
        assertThat(counter().count("Mary had a little lamb").wordCount, equalTo(5))
    }

    fun `When passing word with trailing dot Then return proper word count`() {
        assertThat(counter().count("aaa.").wordCount, equalTo(1))
    }

    fun `When passing words seperated by dash Then treat as single word`() {
        assertThat(counter().count("a-a").wordCount, equalTo(1))
    }

    fun `When passing two same words Then unique word count is 1`() {
        assertThat(counter().count("a a").uniqueWordCount, equalTo(1))
    }

    private fun counter() = WordCounter(neverIsStopWord)

}
