package com.github.christophpickl.codingdojo.wordcount

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class WordCounterTest {

    fun `When counting empty string Then return 0`() {
        assertThat(WordCounter().count(""), equalTo(0))
    }

    fun `When counting whitespace Then return 0`() {
        assertThat(WordCounter().count(" "), equalTo(0))
    }

    fun `When counting single word Then return 1`() {
        assertThat(WordCounter().count("a"), equalTo(1))
    }

    fun `When counting single number Then return 0`() {
        assertThat(WordCounter().count("1"), equalTo(0))
    }

    fun `When counting single non-word Then return 0`() {
        assertThat(WordCounter().count("a1a"), equalTo(0))
    }

    fun `When counting two words separated by space Then return 2`() {
        assertThat(WordCounter().count("a b"), equalTo(2))
    }

    fun `When counting two words separated by two spaces Then return 2`() {
        assertThat(WordCounter().count("a  b"), equalTo(2))
    }

}
