package com.github.christophpickl.codingdojo.wordcount

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class WordCounterIntegrationTest {

    fun `Given real counter When count word "the" Then return 0`() {
        assertThat(counter().count("the"), equalTo(0))
    }

    fun `Given real counter When count word "THE" Then return 1`() {
        assertThat(counter().count("THE"), equalTo(1))
    }

    fun `Given real counter When use sample sentence from requirements 3 Then return proper word count`() {
        assertThat(counter().count("Mary had a little lamb"), equalTo(4))
    }

    fun `Given real counter When use sample sentence from requirements 4 Then return proper word count`() {
        assertThat(counter().count("Humpty-Dumpty sat on a wall. Humpty-Dumpty had a great fall."), equalTo(9))
    }

    private fun counter() = WordCounter(buildStopWordsFilter())

}
