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

    private fun counter() = WordCounter(buildStopWordsFilter())

}
