package com.github.christophpickl.codingdojo.wordcount

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class WordCounterIntegrationTest {

    fun `Given real counter When count word "the" Then word count is 0`() {
        assertThat(counter().count("the").wordCount, equalTo(0))
    }

    fun `Given real counter When count word "THE" Then word count is 1`() {
        assertThat(counter().count("THE").wordCount, equalTo(1))
    }

    fun `Given real counter When use sample sentence from requirements 3 Then word count is 4`() {
        assertThat(counter().count("Mary had a little lamb").wordCount, equalTo(4))
    }

    fun `Given real counter When use sample sentence from requirements 4 Then word count is 9`() {
        assertThat(counter().count("Humpty-Dumpty sat on a wall. Humpty-Dumpty had a great fall.").wordCount, equalTo(9))
    }

    private fun counter() = WordCounter(buildStopWordsFilter())

}
